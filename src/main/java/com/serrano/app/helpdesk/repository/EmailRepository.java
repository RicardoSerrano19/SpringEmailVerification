package com.serrano.app.helpdesk.repository;

import java.util.Optional;

import com.serrano.app.helpdesk.domain.EmailValidator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailValidator,Long>{
    @Query("SELECT e from EmailValidator e WHERE e.token = ?1")
    Optional<EmailValidator> findByToken(String token);
}
