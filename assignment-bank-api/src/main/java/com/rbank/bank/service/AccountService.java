package com.rbank.bank.service;

import com.rbank.bank.model.Account;
import com.rbank.bank.model.Transaction;
import com.rbank.bank.service.dto.CreateAccount;
import com.rbank.bank.service.dto.UpdateAccount;


import java.util.List;

public interface AccountService {
    Account createAccount(CreateAccount account);
    List<Account> getAllAccounts(int page, int size);
    Account getAccountById(Long id);
    Account updateAccount(Long id, UpdateAccount accountDetails);
    void deleteAccount(Long id);
    Transaction transferMoney(Long sourceAccountId, Long destinationAccountId, double amount);
}
