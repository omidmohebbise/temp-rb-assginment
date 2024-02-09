package com.rbank.bank.dao;

import com.rbank.bank.model.Account;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    List<Account> findAll(Pageable pageable);

    Optional<Account> findByAccountId(Long accountId);

    Account save(Account existingAccount);

    void deleteById(Account existingAccount);
}
