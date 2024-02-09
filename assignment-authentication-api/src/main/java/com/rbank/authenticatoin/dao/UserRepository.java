package com.rbank.authenticatoin.dao;

import com.rbank.authenticatoin.model.User;

import java.util.Optional;



public interface UserRepository {
    Optional<User> findByUsername(String username);
    User save(User user);

    long count();
}
