package com.rbank.bank.dao;

import com.rbank.bank.model.UserVerification;

import java.util.Optional;


public interface UserVerificationRepository {
    Optional<UserVerification> findByUsername(String username);

    UserVerification save(UserVerification userVerification);
}
