package com.rbank.authenticatoin.dao.impl;

import com.rbank.authenticatoin.dao.RoleRepository;
import com.rbank.authenticatoin.dao.impl.jpa.RoleJpaRepository;
import com.rbank.authenticatoin.model.Role;
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
