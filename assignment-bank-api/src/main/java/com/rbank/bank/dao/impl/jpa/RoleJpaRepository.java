package com.rbank.bank.dao.impl.jpa;

import com.rbank.bank.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {
    Role findByTitle(String username);

}
