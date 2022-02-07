package com.serrano.app.helpdesk.utils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class CustomMapMessage {
    
    public static void onAuthenticationUnsuccessful(HttpServletResponse response, String message) throws IOException{
        Map<String, String> unsuccesfulMsg = new HashMap<>();
        unsuccesfulMsg.put("status", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        unsuccesfulMsg.put("message", message);
        unsuccesfulMsg.put("timestamp", ZonedDateTime.now().toLocalDateTime().toString());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), unsuccesfulMsg);
    }
}
