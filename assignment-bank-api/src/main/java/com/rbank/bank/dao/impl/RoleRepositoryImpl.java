package com.rbank.bank.dao.impl;

import com.rbank.bank.dao.RoleRepository;
import com.rbank.bank.dao.impl.jpa.RoleJpaRepository;
import com.rbank.bank.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository repository;

    @Override
    public Role findByTitle(String username) {
        return repository.findByTitle(username);
    }
}
