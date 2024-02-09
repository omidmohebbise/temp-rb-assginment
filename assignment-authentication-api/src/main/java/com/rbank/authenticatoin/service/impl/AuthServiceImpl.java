package com.rbank.authenticatoin.service.impl;

import com.rbank.authenticatoin.dao.UserVerificationRepository;
import com.rbank.authenticatoin.model.User;
import com.rbank.authenticatoin.model.UserVerification;
import com.rbank.authenticatoin.service.AuthService;
import com.rbank.authenticatoin.service.dto.EmailVerificationRequest;
import com.rbank.authenticatoin.service.dto.SignUpRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserServiceImpl userServiceImpl;
    private final UserVerificationRepository userVerificationRepository;

    @Transactional
    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = userServiceImpl.createUser(signUpRequest.fullName(),
                signUpRequest.email(), defaultPasswordGenerator());
        createVerificationCode(user);
        return user;
    }

    private void createVerificationCode(User user) {
        String verificationCode = RandomStringUtils.randomNumeric(6);
        UserVerification userVerification = UserVerification.builder()
                .code(verificationCode)
                .user(user)
                .verificationDateTime(LocalDateTime.now())
                .build();
        userVerificationRepository.save(userVerification);
    }

    @Override
    public void verify(EmailVerificationRequest emailVerificationRequest) {
        UserVerification verification = userVerificationRepository.findByUsername(emailVerificationRequest.email())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (verification.getCode().equals(emailVerificationRequest.code())) {
            userServiceImpl.verifyUser(verification.getUser());
        }else{
            throw new IllegalArgumentException("Invalid verification code");
        }

    }

    @Override
    public User signIn(SignUpRequest signInRequest) {
        return null;
    }

    private String defaultPasswordGenerator() {
        return RandomStringUtils.randomAlphanumeric(10) + RandomStringUtils.randomNumeric(2) + RandomStringUtils.randomAlphabetic(2);
    }
}
