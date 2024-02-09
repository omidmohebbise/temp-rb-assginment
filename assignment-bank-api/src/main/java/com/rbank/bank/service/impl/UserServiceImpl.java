package com.rbank.bank.service.impl;


import com.rbank.bank.dao.UserRepository;
import com.rbank.bank.model.Role;
import com.rbank.bank.model.User;
import com.rbank.bank.model.UserPrincipals;
import com.rbank.bank.model.exception.UserNotFound;
import com.rbank.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipals(user.getUsername(), user.getPassword(), user.getRoles().stream().map(Role::getTitle).toList());
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public User createUser(String fullName, String username, String password) {
        return userRepository.save(User.builder()
                .fullName(fullName)
                .username(username)
                .password(password)
                .roles(Set.of(Role.builder().title("USER").build()))
                .enabled(false)
                .verified(false)
                .build());
    }

    public void verifyUser(User user) {
        user.setVerified(true);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFound("User not found")
        );
    }


}