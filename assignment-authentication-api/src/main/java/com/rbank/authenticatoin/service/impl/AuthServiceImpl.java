package com.rbank.authenticatoin.service.impl;

import com.rbank.authenticatoin.dao.UserVerificationRepository;
import com.rbank.authenticatoin.model.Role;
import com.rbank.authenticatoin.model.User;
import com.rbank.authenticatoin.model.UserPrincipals;
import com.rbank.authenticatoin.model.UserVerification;
import com.rbank.authenticatoin.service.AuthService;
import com.rbank.authenticatoin.service.JwtService;
import com.rbank.authenticatoin.service.dto.EmailVerificationRequest;
import com.rbank.authenticatoin.service.dto.JwtAuthenticationToken;
import com.rbank.authenticatoin.service.dto.SignInRequest;
import com.rbank.authenticatoin.service.dto.SignUpRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserServiceImpl userServiceImpl;
    private final UserVerificationRepository userVerificationRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public boolean validateToken(String token) {
        return jwtService.isTokenExpired(token);
    }

    @Transactional
    @Override
    public void signUp(SignUpRequest signUpRequest) {
        User user = userServiceImpl.createUser(signUpRequest.fullName(),
                signUpRequest.email(), passwordEncoder.encode(signUpRequest.password()));
        createVerificationCode(user);
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
        } else {
            throw new IllegalArgumentException("Invalid verification code");
        }

    }

    @Override
    public JwtAuthenticationToken signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        User user = userServiceImpl.findUserByUsername(request.username());
        var userPrincipals = new UserPrincipals(user.getUsername(), user.getPassword(),
                user.getRoles().stream().map(Role::getTitle).toList());
        var jwt = jwtService.generateToken(userPrincipals);
        return new JwtAuthenticationToken(jwt);
    }

}
