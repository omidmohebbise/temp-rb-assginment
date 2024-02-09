package com.rbank.bank.service.impl;

import com.rbank.bank.dao.AccountRepository;
import com.rbank.bank.dao.TransactionRepository;
import com.rbank.bank.model.Account;
import com.rbank.bank.model.Transaction;
import com.rbank.bank.model.User;
import com.rbank.bank.model.exception.AccountNotFoundException;
import com.rbank.bank.model.exception.InsufficientBalanceException;
import com.rbank.bank.service.AccountService;
import com.rbank.bank.service.UserService;
import com.rbank.bank.service.dto.CreateAccount;
import com.rbank.bank.service.dto.TransferMoneyDto;
import com.rbank.bank.service.dto.UpdateAccount;
import com.rbank.bank.service.validator.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserService userService;
    private final AccountValidator accountValidator;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Account createAccount(CreateAccount account) {
        accountValidator.isValid(account);
        User user = userService.getUserById(account.userId());

        Account newAccount = Account.builder()
                .user(user)
                .accountHolderName(account.accountHolderName())
                .accountNumber(createAccountNumber())
                .balance(account.balance())
                .build();
        return accountRepository.save(newAccount);
    }

    private String createAccountNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public List<Account> getAllAccounts(int page, int size) {
        return accountRepository.findAll(Pageable.ofSize(size).withPage(page));
    }

    public Account getAccountById(long id) {
            return accountRepository.findByAccountId(id).orElseThrow(() ->
                    new AccountNotFoundException(" Account not found with id: " + id + " "));
    }

    public Account updateAccount(long id, UpdateAccount accountDetails) {
        accountValidator.isValid(accountDetails);
        Account account = getAccountById(id);
        account.setAccountHolderName(accountDetails.accountHolderName());
        account.setBalance(accountDetails.balance());
        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(getAccountById(accountId));
    }

    public Transaction transferMoney(TransferMoneyDto transferMoneyDto) {
        Account sourceAccount;
        Account destinationAccount;

        try {
            sourceAccount = getAccountById(transferMoneyDto.sourceAccountId());
            destinationAccount = getAccountById(transferMoneyDto.destinationAccountId());
        }catch (Exception e) {
            throw new AccountNotFoundException("Source or Destination Account not found");
        }

        if (sourceAccount.getBalance() >= transferMoneyDto.amount()) {
            sourceAccount.setBalance(sourceAccount.getBalance() - transferMoneyDto.amount());
            destinationAccount.setBalance(destinationAccount.getBalance() + transferMoneyDto.amount());
            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);
            Transaction transaction = new Transaction(sourceAccount, destinationAccount, transferMoneyDto.amount());
            return transactionRepository.save(transaction);
        }else {
            throw new InsufficientBalanceException("Insufficient balance in source account");
        }
    }

}
