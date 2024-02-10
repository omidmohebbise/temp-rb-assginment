package com.rbank.bank.dao;

import com.rbank.bank.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> getRoleByTitle(String username);
}
