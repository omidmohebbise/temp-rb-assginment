package com.rbank.bank.dao.impl;

import com.rbank.bank.dao.UserVerificationRepository;
import com.rbank.bank.dao.impl.jpa.UserVerificationJpaRepository;
import com.rbank.bank.model.UserVerification;
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
