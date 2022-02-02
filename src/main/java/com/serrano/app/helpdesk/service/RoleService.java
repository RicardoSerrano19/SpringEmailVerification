package com.serrano.app.helpdesk.service;

import com.serrano.app.helpdesk.domain.dto.RoleDTO;
import com.serrano.app.helpdesk.domain.dto.RolesDTO;

public interface RoleService {
    RoleDTO get(String name);
    RolesDTO getAll();
}
