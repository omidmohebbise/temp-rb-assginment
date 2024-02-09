package com.rbank.authenticatoin.service;


import com.rbank.authenticatoin.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    User createUser(String fullName, String username, String password);

    void verifyUser(User user);
}