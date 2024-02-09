package com.rbank.authenticatoin.dao.impl;

import com.rbank.authenticatoin.dao.UserVerificationRepository;
import com.rbank.authenticatoin.dao.impl.jpa.UserVerificationJpaRepository;
import com.rbank.authenticatoin.model.UserVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserVerificationRepositoryImpl implements UserVerificationRepository {

    private final UserVerificationJpaRepository repository;
    @Override
    public Optional<UserVerification> findByUsername(String username) {
        return repository.findTopByUserUsernameOrderByVerificationDateTimeDesc(username);
    }

    @Override
    public UserVerification save(UserVerification userVerification) {
        return repository.save(userVerification);
    }
}
