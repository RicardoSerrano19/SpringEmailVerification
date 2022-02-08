package com.serrano.app.helpdesk.controller;

import com.serrano.app.helpdesk.service.EmailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmailValidatorController {
    @Autowired
    EmailServiceImpl emailService;

    @GetMapping("/emails")
    public ResponseEntity<Object> confirmEmail(@RequestParam(name = "token") String token){
        return new ResponseEntity<Object>(emailService.findByToken(token), HttpStatus.OK);
    }
}
