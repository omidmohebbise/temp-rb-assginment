package com.rbank.bank.service.dto;


public record CreateAccount(String accountHolderName, double balance,Long userId) {
}
