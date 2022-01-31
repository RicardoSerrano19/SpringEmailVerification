package com.serrano.app.helpdesk.controller;

import java.util.List;

import javax.validation.Valid;

import com.serrano.app.helpdesk.domain.User;
import com.serrano.app.helpdesk.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
    } 

    @GetMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
    }

}
