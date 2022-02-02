package com.serrano.app.helpdesk.exception;

public class RoleNotFoundException extends RuntimeException{
    
    public RoleNotFoundException(String name){
        super(String.format("role name: %s not found", name));
    }
}
