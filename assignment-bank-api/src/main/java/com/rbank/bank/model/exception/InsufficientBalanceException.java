package com.rbank.bank.model.exception;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String insufficientBalanceInSourceAccount) {
        super(insufficientBalanceInSourceAccount);
    }
}
