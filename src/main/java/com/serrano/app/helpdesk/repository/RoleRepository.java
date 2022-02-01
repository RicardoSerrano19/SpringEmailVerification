package com.serrano.app.helpdesk.repository;

import java.util.Optional;

import com.serrano.app.helpdesk.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
}