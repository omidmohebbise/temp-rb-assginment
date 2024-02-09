package com.rbank.authenticatoin.service.impl;


import com.rbank.authenticatoin.dao.UserRepository;
import com.rbank.authenticatoin.model.Role;
import com.rbank.authenticatoin.model.User;
import com.rbank.authenticatoin.model.UserPrincipals;
import com.rbank.authenticatoin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipals(user.getUsername(), user.getPassword(), user.getRoles().stream().map(Role::getTitle).toList());
    }


    public User createUser(String fullName, String username, String password) {
        return userRepository.save(User.builder()
                .fullName(fullName)
                .username(username)
                .password(password)
                .enabled(false)
                .verified(false)
                .build());
    }

    public void verifyUser(User user) {
        user.setVerified(true);
        user.setEnabled(true);
        userRepository.save(user);
    }
}