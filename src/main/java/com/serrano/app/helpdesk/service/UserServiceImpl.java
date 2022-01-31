package com.serrano.app.helpdesk.service;

import java.util.List;
import java.util.ArrayList;
import com.serrano.app.helpdesk.domain.User;
import com.serrano.app.helpdesk.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public User get(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
