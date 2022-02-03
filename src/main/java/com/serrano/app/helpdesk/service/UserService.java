package com.serrano.app.helpdesk.service;

import com.serrano.app.helpdesk.domain.dto.RoleToUserDTO;
import com.serrano.app.helpdesk.domain.dto.UserDTO;
import com.serrano.app.helpdesk.domain.dto.UserRoleDTO;

public interface UserService {
    UserDTO save(UserDTO user);
    UserDTO get(String username);
    UserRoleDTO findAll();
    RoleToUserDTO addRole(String email, String role);
}
