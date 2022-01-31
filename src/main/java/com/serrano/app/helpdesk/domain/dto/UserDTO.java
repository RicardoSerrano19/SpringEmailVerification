package com.serrano.app.helpdesk.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO implements Serializable{
    
    @NotEmpty(message = "firstName should not be empty")
    @Size(min = 5, max = 200, message 
      = "firstName must be between 5 and 100 characters")
    private String firstName;
    @NotEmpty(message = "lastName should not be empty")
    @Size(min = 5, max = 200, message 
    = "lastName must be between 5 and 100 characters")
    private String lastName;
    @NotEmpty(message = "email should not be empty")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
    message = "email must be valid")
    private String email;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 5, max = 100, message 
    = "password must be between 8 and 100 characters")
    private String password;
}
