package com.rbank.bank.dao;

import com.rbank.bank.model.User;

import java.util.Optional;



public interface UserRepository {
    Optional<User> findByUsername(String username);
    User save(User user);

    long count();
}
