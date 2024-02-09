package com.rbank.bank.service.impl;

import com.rbank.bank.dao.AccountRepository;
import com.rbank.bank.dao.TransactionRepository;
import com.rbank.bank.model.Account;
import com.rbank.bank.model.Transaction;
import com.rbank.bank.model.User;
import com.rbank.bank.model.exception.AccountNotFoundException;
import com.rbank.bank.model.exception.InsufficientBalanceException;
import com.rbank.bank.model.exception.UserNotFound;
import com.rbank.bank.service.AccountService;
import com.rbank.bank.service.UserService;
import com.rbank.bank.service.dto.CreateAccount;
import com.rbank.bank.service.dto.UpdateAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserService userService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Account createAccount(CreateAccount account) {
        User user = userService.findUserById(account.userId()).orElseThrow(
                () -> new UserNotFound("User not found")
        );
        Account newAccount = Account.builder()
                .user(user)
                .accountHolderName(account.accountHolderName())
                .accountNumber(account.accountNumber())
                .balance(account.balance())
                .build();
        return accountRepository.save(newAccount);
    }

    public List<Account> getAllAccounts(int page, int size) {
        return accountRepository.findAll(Pageable.ofSize(size).withPage(page));
    }

    public Account getAccountById(Long id) {
            return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException());
    }

    public Account updateAccount(Long id, UpdateAccount accountDetails) {
        Account account = getAccountById(id);
        account.setAccountNumber(accountDetails.accountNumber());
        account.setAccountHolderName(accountDetails.accountHolderName());
        account.setBalance(accountDetails.balance());
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Transaction transferMoney(Long sourceAccountId, Long destinationAccountId, double amount) {
        Account sourceAccount;
        Account destinationAccount;

        try {
            sourceAccount = getAccountById(sourceAccountId);
            destinationAccount = getAccountById(destinationAccountId);
        }catch (Exception e) {
            throw new AccountNotFoundException("Source or Destination Account not found");
        }

        if (sourceAccount.getBalance() >= amount) {
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            destinationAccount.setBalance(destinationAccount.getBalance() + amount);
            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);
            Transaction transaction = new Transaction(sourceAccount, destinationAccount, amount);
            return transactionRepository.save(transaction);
        }else {
            throw new InsufficientBalanceException("Insufficient balance in source account");
        }
    }



}
