package com.serrano.app.helpdesk.exception;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(EmailNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        APIException exception = new APIException(
            ex.getMessage(), 
            status,
            ZonedDateTime.now());
        return new ResponseEntity<>(exception,status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            
        String error = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .findFirst()
            .get().getDefaultMessage();
           

        APIException exception = new APIException(
            error, 
            status,
            ZonedDateTime.now());


        return new ResponseEntity<>(exception, status);
    }
}
