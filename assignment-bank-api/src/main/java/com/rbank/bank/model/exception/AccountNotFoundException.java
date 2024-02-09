package com.rbank.bank.model.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException() {
    }
}
