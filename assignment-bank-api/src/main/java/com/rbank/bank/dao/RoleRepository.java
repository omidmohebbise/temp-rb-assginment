package com.rbank.bank.dao;

import com.rbank.bank.model.Role;

public interface RoleRepository {
    Role findByTitle(String username);
}
