package com.rbank.authenticatoin.service.dto;

public record EmailVerificationRequest(String code, String email){
}
