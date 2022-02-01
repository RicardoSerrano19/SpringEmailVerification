package com.serrano.app.helpdesk.service;

import java.util.List;
import com.serrano.app.helpdesk.domain.dto.UserDTO;
import com.serrano.app.helpdesk.domain.dto.UserRoleDTO;

public interface UserService {
    UserDTO save(UserDTO user);
    UserDTO get(String username);
    UserRoleDTO findAll();
}
