package com.rbank.bank.service.impl;

import com.rbank.bank.dao.AccountRepository;
import com.rbank.bank.model.Account;
import com.rbank.bank.model.User;
import com.rbank.bank.model.exception.AccountNotFoundException;
import com.rbank.bank.service.UserService;
import com.rbank.bank.service.dto.CreateAccount;
import com.rbank.bank.service.dto.UpdateAccount;
import com.rbank.bank.service.validator.AccountValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class AccountServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private AccountValidator accountValidator;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testCreateAccount() {
        // Given
        Long userId = 1L;
        String accountHolderName = "John Doe";
        double balance = 100.0;
        CreateAccount createAccount = new CreateAccount(accountHolderName, balance, userId);
        User user = new User();

        Account account = Account.builder()
                .accountHolderName(accountHolderName)
                .balance(balance)
                .user(user)
                .build();

        // Mock behavior
        when(userService.getUserById(userId)).thenReturn(user);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // When
        Account createdAccount = accountService.createAccount(createAccount);

        // Then
        assertNotNull(createdAccount);
        assertEquals(accountHolderName, createdAccount.getAccountHolderName());
        assertEquals(createdAccount.getBalance(), createdAccount.getBalance());

    }

    @Test
    public void getAccountById() {
        when(accountRepository.findByAccountId(anyLong())).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class,
                () -> accountService.getAccountById(1L));
    }

    @Test
    public void updateAccount() {
        // Given
        Long accountId = 1L;
        String accountHolderName = "John Doe";
        double balance = 100.0;
        UpdateAccount updateAccount = new UpdateAccount(accountHolderName, balance);
        Account account = Account.builder()
                .accountHolderName(accountHolderName)
                .balance(balance)
                .build();

        // Mock behavior
        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // When
        Account updatedAccount = accountService.updateAccount(accountId, updateAccount);

        // Then
        assertNotNull(updatedAccount);
        assertEquals(accountHolderName, updatedAccount.getAccountHolderName());
        assertEquals(balance, updatedAccount.getBalance());
    }

    @Test
    public void deleteAccount() {
        when(accountRepository.findByAccountId(anyLong())).thenReturn(Optional.empty());
        var result = assertThrows(AccountNotFoundException.class,
                () -> accountService.deleteAccount(1L));
        assertEquals(" Account not found with id: 1 ", result.getMessage());

    }
}