package com.rbank.authenticatoin.controller;

import com.rbank.bank.service.AuthService;
import com.rbank.bank.service.dto.*;
import com.rbank.bank.service.validator.AccountValidator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdentityControllerTest {
    @InjectMocks
    private AccountValidator accountValidator;

    @BeforeEach
    void setUp() {
        accountValidator = new AccountValidator();
    }



    @Mock
    private AuthService authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

}