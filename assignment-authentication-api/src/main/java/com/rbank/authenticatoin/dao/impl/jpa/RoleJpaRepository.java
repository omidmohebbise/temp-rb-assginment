package com.rbank.authenticatoin.dao.impl.jpa;

import com.rbank.authenticatoin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {
    Role findByTitle(String username);

}
