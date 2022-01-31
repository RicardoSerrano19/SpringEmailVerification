package com.serrano.app.helpdesk.service;

import java.util.List;
import com.serrano.app.helpdesk.domain.dto.UserDTO;

public interface UserService {
    UserDTO save(UserDTO user);
    UserDTO get(String username);
    List<UserDTO> findAll();
}
