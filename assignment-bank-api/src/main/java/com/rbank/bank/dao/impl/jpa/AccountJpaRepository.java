package com.rbank.bank.dao.impl.jpa;

import com.rbank.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<Account,Long> {
}
