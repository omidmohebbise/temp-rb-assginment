package com.rbank.authenticatoin.dao;

import com.rbank.authenticatoin.model.UserVerification;

import java.util.Optional;


public interface UserVerificationRepository {
    Optional<UserVerification> findByUsername(String username);

    UserVerification save(UserVerification userVerification);
}
