package com.rbank.bank.service.dto;


public record UpdateAccount(String accountNumber, String accountHolderName, double balance) {
}
