package com.serrano.app.helpdesk.exception;

public class EmailDuplicatedException extends RuntimeException{
    public EmailDuplicatedException(String email){
        super(String.format("Email %s has been registred before", email));
    }
}
