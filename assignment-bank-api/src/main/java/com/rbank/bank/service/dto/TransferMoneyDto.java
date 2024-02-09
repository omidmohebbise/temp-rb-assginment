package com.rbank.bank.service.dto;

public record TransferMoneyDto (Long sourceAccountId, Long destinationAccountId,
                               double amount){
}
