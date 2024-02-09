package com.rbank.bank.dao.impl;


import com.rbank.bank.dao.AccountRepository;
import com.rbank.bank.dao.impl.jpa.AccountJpaRepository;
import com.rbank.bank.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository repository;

    @Override
    public List<Account> findAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Account save(Account existingAccount) {
        return repository.save(existingAccount);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
