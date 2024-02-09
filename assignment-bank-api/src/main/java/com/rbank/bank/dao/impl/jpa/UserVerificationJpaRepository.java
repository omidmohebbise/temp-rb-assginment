package com.rbank.bank.dao.impl.jpa;

import com.rbank.bank.model.UserVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserVerificationJpaRepository extends CrudRepository<UserVerification, Long> {
    Optional<UserVerification> findTopByUserUsernameOrderByVerificationDateTimeDesc(String username);


}
