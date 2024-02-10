package com.rbank.bank.service.impl;

import com.rbank.bank.dao.AccountRepository;
import com.rbank.bank.dao.TransactionRepository;
import com.rbank.bank.model.Account;
import com.rbank.bank.model.Transaction;
import com.rbank.bank.model.User;
import com.rbank.bank.model.exception.AccountNotFoundException;
import com.rbank.bank.model.exception.InsufficientBalanceException;
import com.rbank.bank.service.UserService;
import com.rbank.bank.service.dto.CreateAccount;
import com.rbank.bank.service.dto.TransferMoney;
import com.rbank.bank.service.dto.UpdateAccount;
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
    private AccountRepository accountRepository;
    @Mock
    TransactionRepository transactionRepository;

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
        long accountId = 1L;
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

    @Test
    void testTransferMoney_SuccessfulTransfer() {
        // Mock data
        Account sourceAccount = new Account();
        sourceAccount.setId(1L);
        sourceAccount.setBalance(1000.0);

        Account destinationAccount = new Account();
        destinationAccount.setId(2L);
        destinationAccount.setBalance(500.0);

        Transaction transaction = new Transaction(sourceAccount, destinationAccount, 300.0);

        TransferMoney transferMoney = new TransferMoney(1L, 2L, 300.0);

        // Mock repository behavior
        when(accountRepository.findByAccountId(1L)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findByAccountId(2L)).thenReturn(Optional.of(destinationAccount));
        when(transactionRepository.save(any())).thenReturn(transaction);

        // Execute the method
        Transaction transactionResult = accountService.transferMoney(transferMoney);

        // Verify balances and transaction
        assertEquals(700.0, sourceAccount.getBalance());
        assertEquals(800.0, destinationAccount.getBalance());
        assertEquals(transaction.getAmount(), transactionResult.getAmount());
    }

    @Test
    void testTransferMoney_InvalidSourceAccount() {
        // Mock data

        Account destinationAccount = new Account();
        destinationAccount.setId(2L);
        destinationAccount.setBalance(500.0);

        TransferMoney transferMoney = new TransferMoney(1L, 2L, 3000.0);

        // Mock repository behavior
        when(accountRepository.findByAccountId(1L)).thenReturn(Optional.empty());
        when(accountRepository.findByAccountId(2L)).thenReturn(Optional.of(destinationAccount));

        // Execute the method
        var result = assertThrows(AccountNotFoundException.class,
                () -> accountService.transferMoney(transferMoney));
        assertEquals("Source or Destination Account not found" , result.getMessage());
    }

    @Test
    void testTransferMoney_InvalidDestinationAccount() {
        // Mock data

        Account source = new Account();
        source.setId(2L);
        source.setBalance(500.0);

        TransferMoney transferMoney = new TransferMoney(1L, 2L, 3000.0);

        // Mock repository behavior
        when(accountRepository.findByAccountId(1L)).thenReturn(Optional.of(source));
        when(accountRepository.findByAccountId(2L)).thenReturn(Optional.empty());

        // Execute the method
        var result = assertThrows(AccountNotFoundException.class,
                () -> accountService.transferMoney(transferMoney));
        assertEquals("Source or Destination Account not found" , result.getMessage());
    }

    @Test
    void testTransferMoney_InsufficientBalance() {
        // Mock data
        Account sourceAccount = new Account();
        sourceAccount.setId(1L);
        sourceAccount.setBalance(100.0);

        Account destinationAccount = new Account();
        destinationAccount.setId(2L);
        destinationAccount.setBalance(500.0);

        TransferMoney transferMoney = new TransferMoney(1L, 2L, 3000.0);

        // Mock repository behavior
        when(accountRepository.findByAccountId(1L)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findByAccountId(2L)).thenReturn(Optional.of(destinationAccount));

        // Execute the method
        var result = assertThrows(InsufficientBalanceException.class,
                () -> accountService.transferMoney(transferMoney));
        assertEquals("Insufficient balance in source account", result.getMessage());
    }
}