package com.serrano.app.helpdesk.exception;

public class TokenNotFoundException extends RuntimeException{
    
    public TokenNotFoundException(String name){
        super(String.format("token name: %s not found", name));
    }
}
