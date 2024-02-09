package com.rbank.authenticatoin.dao;

import com.rbank.authenticatoin.model.Role;

public interface RoleRepository {
    Role findByTitle(String username);
}
