package com.serrano.app.helpdesk.controller;

import java.util.List;

import javax.validation.Valid;

import com.serrano.app.helpdesk.domain.dto.UserDTO;
import com.serrano.app.helpdesk.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO user){
        return new ResponseEntity<UserDTO>(userService.save(user), HttpStatus.CREATED);
    } 

    @GetMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAll(){
        return new ResponseEntity<List<UserDTO>>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{email}")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email){
        return new ResponseEntity<UserDTO>(userService.get(email), HttpStatus.OK);
    }

}
