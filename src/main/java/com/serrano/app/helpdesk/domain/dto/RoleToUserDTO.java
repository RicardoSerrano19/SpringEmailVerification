package com.serrano.app.helpdesk.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleToUserDTO {
    @NotEmpty(message = "email should not be empty")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
    message = "email must be valid")
    private String email;
    @NotEmpty(message = "role should not be empty")
    private String role;
}
