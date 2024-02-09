package com.rbank.authenticatoin.controller;

import com.rbank.authenticatoin.service.AuthService;
import com.rbank.authenticatoin.service.dto.EmailVerificationRequest;
import com.rbank.authenticatoin.service.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/auth")
public class IdentityController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestBody EmailVerificationRequest emailVerificationRequest) {
        authService.verify(emailVerificationRequest);
        return ResponseEntity.ok("user verified");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignUpRequest signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

}
