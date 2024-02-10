package com.rbank.bank.service.dto;

public record TransferMoney(Long sourceAccountId, Long destinationAccountId, double amount) {
}
