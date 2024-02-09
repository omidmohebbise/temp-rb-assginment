package com.rbank.bank.service;

import com.rbank.bank.service.dto.EmailVerificationRequest;
import com.rbank.bank.service.dto.JwtAuthenticationToken;
import com.rbank.bank.service.dto.SignInRequest;
import com.rbank.bank.service.dto.SignUpRequest;

public interface AuthService {
    void signUp(SignUpRequest signUpRequest);

    void verify(EmailVerificationRequest emailVerificationRequest);

    JwtAuthenticationToken signIn(SignInRequest request);

    boolean validateToken(String token);
}