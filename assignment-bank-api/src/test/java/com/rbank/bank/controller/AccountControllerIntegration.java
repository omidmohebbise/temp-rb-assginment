package com.rbank.bank.controller;

import com.rbank.bank.dao.AccountRepository;
import com.rbank.bank.model.Account;
import com.rbank.bank.model.User;
import com.rbank.bank.service.UserService;
import com.rbank.util.BaseIntegration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerIntegration  extends BaseIntegration {

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testCreateAccount() {
        // given

        Account account = new Account();
        account.setAccountNumber("1234567890");
        account.setAccountHolderName("omid");
        account.setBalance(1000.0);
        account.setUser(User.builder().id(1L).build());


        // when
        Account savedAccount = accountRepository.save(account);

        // then
        assertEquals(account.getAccountNumber(), savedAccount.getAccountNumber());
    }
}