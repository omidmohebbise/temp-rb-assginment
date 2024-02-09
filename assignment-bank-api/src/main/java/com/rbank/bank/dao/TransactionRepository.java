package com.rbank.bank.dao;

import com.rbank.bank.model.Transaction;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
}
