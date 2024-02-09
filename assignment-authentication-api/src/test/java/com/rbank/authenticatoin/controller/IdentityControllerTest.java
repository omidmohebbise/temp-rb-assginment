package com.rbank.authenticatoin.controller;

import com.rbank.authenticatoin.service.AuthService;
import com.rbank.authenticatoin.service.dto.EmailVerificationRequest;
import com.rbank.authenticatoin.service.dto.JwtAuthenticationToken;
import com.rbank.authenticatoin.service.dto.SignInRequest;
import com.rbank.authenticatoin.service.dto.SignUpRequest;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdentityControllerTest {
    @InjectMocks
    private IdentityController identityController;

    @Mock
    private AuthService authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void signUpSuccessfully() {
        SignUpRequest signUpRequest = mock(SignUpRequest.class);
        doNothing().doThrow(new RuntimeException()).when(authService).signUp(signUpRequest);

        ResponseEntity<?> response = identityController.signUp(signUpRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User signed up successfully. Please verify your username", response.getBody());
    }

    @Test
    public void verifyEmailSuccessfully() {
        EmailVerificationRequest emailVerificationRequest = mock(EmailVerificationRequest.class);
        doNothing().when(authService).verify(emailVerificationRequest);

        ResponseEntity<?> response = identityController.verifyEmail(emailVerificationRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("user verified", response.getBody());
    }

    @Test
    public void signInSuccessfully() {
        SignInRequest signInRequest = mock(SignInRequest.class);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken("sample-token");
        when(authService.signIn(signInRequest)).thenReturn(jwtAuthenticationToken);

        ResponseEntity<?> response = identityController.signIn(signInRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jwtAuthenticationToken.toString(), response.getBody().toString());
    }

    @Test
    public void validateTokenSuccessfully() {
        JwtAuthenticationToken token = mock(JwtAuthenticationToken.class);
        when(authService.validateToken(token.token())).thenReturn(true);

        ResponseEntity<String> response = identityController.validateToken(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Token is valid", response.getBody());
    }

    @Test
    public void validateTokenUnsuccessfully() {
        JwtAuthenticationToken token = mock(JwtAuthenticationToken.class);
        when(authService.validateToken(token.token())).thenReturn(false);

        ResponseEntity<String> response = identityController.validateToken(token);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid token", response.getBody());
    }

    @Test
    public void handleBadCredentialsException() {
        Exception e = new BadCredentialsException("Bad credentials");
        ResponseEntity<String> response = identityController.handleException(e);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("An error occurred: Bad credentials", response.getBody());
    }

    @Test
    public void handleEntityNotFoundException() {
        Exception e = new EntityNotFoundException("Entity not found");
        ResponseEntity<String> response = identityController.handleException(e);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("An error occurred: Entity not found", response.getBody());
    }

    @Test
    public void handleIllegalArgumentException() {
        Exception e = new IllegalArgumentException("Illegal argument");
        ResponseEntity<String> response = identityController.handleException(e);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("An error occurred: Illegal argument", response.getBody());
    }

    @Test
    public void handleGenericException() {
        Exception e = new Exception("Generic exception");
        ResponseEntity<String> response = identityController.handleException(e);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: Generic exception", response.getBody());
    }
}