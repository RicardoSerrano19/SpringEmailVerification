package com.serrano.app.helpdesk.domain.dto;

import java.io.Serializable;

import com.serrano.app.helpdesk.enums.RoleName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable{
    private RoleName name;
}
