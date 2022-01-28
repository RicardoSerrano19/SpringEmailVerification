package com.serrano.app.helpdesk.exception;

public class UserNotFoundException extends RuntimeException{
    
    public UserNotFoundException(String username){
        super(String.format("Username %s not found", username));
    }
}
