package com.rbank.bank.dao.impl;

import com.rbank.bank.dao.TransactionRepository;
import com.rbank.bank.dao.impl.jpa.TransactionJpaRepository;
import com.rbank.bank.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionJpaRepository repository;
    @Override
    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }
}
