package com.rbank.authenticatoin.dao.impl.jpa;

import com.rbank.authenticatoin.model.UserVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserVerificationJpaRepository extends CrudRepository<UserVerification, Long> {
    Optional<UserVerification> findTopByUserUsernameOrderByVerificationDateTimeDesc(String username);


}
