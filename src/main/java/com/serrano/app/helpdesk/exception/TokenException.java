package com.serrano.app.helpdesk.exception;

public class TokenException extends RuntimeException{
    
    public TokenException(String message){
        super(String.format("%s", message));
    }
}
