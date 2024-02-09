package com.rbank.bank.dao.impl.jpa;

import com.rbank.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {

}
