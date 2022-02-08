package com.serrano.app.helpdesk.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.serrano.app.helpdesk.utils.CustomMapMessage;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component("customEntryPoint")
@Slf4j
public class CustomEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        CustomMapMessage.onError(response, authException.getMessage(), HttpStatus.UNAUTHORIZED);
        log.error("Error en AuthenticationException: {}", authException.getMessage());
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        CustomMapMessage.onError(response, accessDeniedException.getMessage(), HttpStatus.FORBIDDEN);
        log.error("Error en AccessDeniedException: {}", accessDeniedException.getMessage());
    }
    
}
