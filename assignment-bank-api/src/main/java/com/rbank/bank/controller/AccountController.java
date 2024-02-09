package com.rbank.bank.controller;

import com.rbank.bank.model.Account;
import com.rbank.bank.model.Transaction;
import com.rbank.bank.service.AccountService;
import com.rbank.bank.service.dto.CreateAccount;
import com.rbank.bank.service.dto.UpdateAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateAccount createAccount) {
        Account account = accountService.createAccount(createAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        List<Account> accounts = accountService.getAllAccounts(page, size);
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody UpdateAccount accountDetails) {
        Account updatedAccount = accountService.updateAccount(id, accountDetails);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transferMoney(@RequestParam Long sourceAccountId,
                                                     @RequestParam Long destinationAccountId,
                                                     @RequestParam double amount) {
        Transaction transaction = accountService.transferMoney(sourceAccountId, destinationAccountId, amount);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
