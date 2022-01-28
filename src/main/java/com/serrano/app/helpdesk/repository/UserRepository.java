package com.serrano.app.helpdesk.repository;

import com.serrano.app.helpdesk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
