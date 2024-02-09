package com.rbank.bank.dao;

import com.rbank.bank.model.Account;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    List<Account> findAll(Pageable pageable);

    Optional<Account> findById(Long id);

    Account save(Account existingAccount);

    void deleteById(Long id);
}
