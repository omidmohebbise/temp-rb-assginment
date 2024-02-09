package com.rbank.bank.service.dto;


public record CreateAccount(String accountNumber, String accountHolderName, double balance,Long userId) {
}
