package com.serrano.app.helpdesk.repository;

import java.util.Optional;

import com.serrano.app.helpdesk.domain.Role;
import com.serrano.app.helpdesk.enums.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<RoleName> findByName(RoleName roleName);
}