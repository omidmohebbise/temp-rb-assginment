package com.rbank.authenticatoin.service;

import com.rbank.authenticatoin.model.User;
import com.rbank.authenticatoin.service.dto.EmailVerificationRequest;
import com.rbank.authenticatoin.service.dto.JwtAuthenticationToken;
import com.rbank.authenticatoin.service.dto.SignInRequest;
import com.rbank.authenticatoin.service.dto.SignUpRequest;

public interface AuthService {
    User signUp(SignUpRequest signUpRequest);

    void verify(EmailVerificationRequest emailVerificationRequest);

    JwtAuthenticationToken signIn(SignInRequest request);

    boolean validateToken(String token);
}