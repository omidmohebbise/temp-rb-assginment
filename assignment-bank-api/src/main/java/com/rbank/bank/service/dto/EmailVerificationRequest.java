package com.rbank.bank.service.dto;

public record EmailVerificationRequest(String code, String email){
}
