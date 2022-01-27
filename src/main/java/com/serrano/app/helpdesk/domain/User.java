package com.serrano.app.helpdesk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.GenerationType;

@Entity
@Table
public class User {

    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    private Long id;
    @NotEmpty
    @Size(min = 5, max = 200, message 
      = "firstName must be between 5 and 100 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 5, max = 200, message 
    = "lastName must be between 5 and 100 characters")
    private String lastName;
    @NotEmpty
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
    message = "email must be valid")
    private String email;
    @NotEmpty
    @Size(min = 5, max = 100, message 
    = "password must be between 8 and 100 characters")
    private String password;
    private Boolean locked;
    private Boolean enabled;
}


/*
import java.util.ArrayList;
import java.util.Collection;
    private Collection<Role> roles = new ArrayList<>();
*/