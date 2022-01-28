package com.serrano.app.helpdesk.service;

import java.util.List;
import com.serrano.app.helpdesk.domain.User;

public interface UserService {
    User save(User user);
    User get(String username);
    List<User> findAll();
}
