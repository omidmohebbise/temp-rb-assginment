package com.rbank.authenticatoin.controller;

import com.rbank.authenticatoin.service.AuthService;
import com.rbank.authenticatoin.service.dto.EmailVerificationRequest;
import com.rbank.authenticatoin.service.dto.JwtAuthenticationToken;
import com.rbank.authenticatoin.service.dto.SignInRequest;
import com.rbank.authenticatoin.service.dto.SignUpRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/auth")
public class IdentityController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
        return ResponseEntity.ok("User signed up successfully. Please verify your username");
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestBody EmailVerificationRequest emailVerificationRequest) {
        authService.verify(emailVerificationRequest);
        return ResponseEntity.ok("user verified");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestBody JwtAuthenticationToken token) {
        boolean isValid = authService.validateToken(token.token());
        if (isValid) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        if (e instanceof BadCredentialsException)
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        else if (e instanceof IllegalArgumentException)
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        else if (e instanceof EntityNotFoundException)
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
