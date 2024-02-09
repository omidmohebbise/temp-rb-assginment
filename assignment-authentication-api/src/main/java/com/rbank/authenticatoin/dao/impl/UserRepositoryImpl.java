package com.rbank.authenticatoin.dao.impl;

import com.rbank.authenticatoin.dao.UserRepository;
import com.rbank.authenticatoin.dao.impl.jpa.UserJpaRepository;
import com.rbank.authenticatoin.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repository;

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
