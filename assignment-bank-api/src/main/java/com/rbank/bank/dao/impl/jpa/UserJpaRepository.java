package com.rbank.bank.dao.impl.jpa;

import com.rbank.bank.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
