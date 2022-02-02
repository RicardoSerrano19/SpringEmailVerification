package com.serrano.app.helpdesk.domain.dto;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesDTO implements Serializable{
    private List<RoleDTO> roles;
}
