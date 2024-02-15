package com.rbank.bank.service;


import com.rbank.bank.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    User createUser(String fullName, String username, String password);

    void verifyUser(User user);
    User getUserById(Long aLong);


}