package com.rbank.bank.service.validator;

import com.rbank.bank.service.dto.CreateAccount;
import com.rbank.bank.service.dto.UpdateAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountValidator {

    public void isValid(CreateAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account info cannot be null");
        }

        isValidAccountHolderName(account.accountHolderName());
        isValidBalance(account.balance());
        isValidUserId(account.userId());
    }
    public void isValid(UpdateAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account info cannot be null");
        }
        isValidAccountHolderName(account.accountHolderName());
        isValidBalance(account.balance());
    }

    private void isValidUserId(Long aLong) {
        if (aLong == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }
    }

    private void isValidAccountHolderName(String accountHolderName) {
        if (accountHolderName == null || accountHolderName.isEmpty())
            throw new IllegalArgumentException("Account holder name cannot be null or empty");
    }

    private void isValidBalance(double balance) {
        if (balance < 0)
            throw new IllegalArgumentException("Account balance cannot be less than 0");
    }
}

