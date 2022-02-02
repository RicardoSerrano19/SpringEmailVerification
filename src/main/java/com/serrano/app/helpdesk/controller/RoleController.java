package com.serrano.app.helpdesk.controller;

import com.serrano.app.helpdesk.domain.dto.RoleDTO;
import com.serrano.app.helpdesk.domain.dto.RolesDTO;
import com.serrano.app.helpdesk.service.RoleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class RoleController {
    @Autowired
    RoleServiceImpl roleService;

    @GetMapping("/roles")
    public ResponseEntity<RolesDTO> getAll(){
        return new ResponseEntity<RolesDTO>(roleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/roles/{name}")
    public ResponseEntity<RoleDTO> get(@PathVariable String name){
        return new ResponseEntity<RoleDTO>(roleService.get(name), HttpStatus.OK);
    }
}


