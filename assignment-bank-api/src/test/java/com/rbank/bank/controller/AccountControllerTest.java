package com.rbank.bank.controller;

import com.rbank.bank.model.Account;
import com.rbank.bank.service.AccountService;
import com.rbank.bank.service.dto.CreateAccount;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountController accountController;
    @Test
    void createAccountTest() {
        CreateAccount createAccount = new CreateAccount(
                "John Doe",
                1000.0,
                1L
        );
        Account mockAccount = new Account();

        when(accountService.createAccount(any(CreateAccount.class))).thenReturn(mockAccount);

        ResponseEntity<?> response = accountController.createAccount(createAccount);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockAccount, response.getBody());
    }
}