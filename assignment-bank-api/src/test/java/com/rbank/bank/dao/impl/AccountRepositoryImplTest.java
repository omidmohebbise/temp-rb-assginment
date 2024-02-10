package com.rbank.bank.dao.impl;

import com.rbank.bank.dao.AccountRepository;
import com.rbank.bank.model.Account;
import com.rbank.bank.model.User;
import com.rbank.util.BaseIntegrationTest;
import com.rbank.util.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountRepositoryImplTest extends BaseIntegrationTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void save() {
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

    @Test
    public void delete() {
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
        assertDoesNotThrow(() -> accountRepository.deleteById(savedAccount));

    }
}