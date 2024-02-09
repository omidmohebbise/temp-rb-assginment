package com.rbank.authenticatoin.service;

import com.rbank.authenticatoin.model.User;
import com.rbank.authenticatoin.service.dto.EmailVerificationRequest;
import com.rbank.authenticatoin.service.dto.JwtAuthenticationResponse;
import com.rbank.authenticatoin.service.dto.SignInRequest;
import com.rbank.authenticatoin.service.dto.SignUpRequest;

public interface AuthService {
    User signUp(SignUpRequest signUpRequest);

    void verify(EmailVerificationRequest emailVerificationRequest);

    JwtAuthenticationResponse signIn(SignInRequest request);
}