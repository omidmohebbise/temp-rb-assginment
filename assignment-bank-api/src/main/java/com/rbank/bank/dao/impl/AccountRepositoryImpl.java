package com.rbank.bank.dao.impl;


import com.rbank.bank.dao.AccountRepository;
import com.rbank.bank.dao.impl.jpa.AccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository repository;
}
