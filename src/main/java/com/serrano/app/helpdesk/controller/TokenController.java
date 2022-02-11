package com.serrano.app.helpdesk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.serrano.app.helpdesk.service.TokenServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TokenController {

    @Autowired
    private TokenServiceImpl tokenService;

    @GetMapping("/token/refresh")
    public void refresh(HttpServletRequest request, HttpServletResponse response){
        tokenService.refresh(request, response);
    }
}
