package com.serrano.app.helpdesk.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthorizationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        //Dont apply filter for specific paths
        if(request.getServletPath().equals("/login")){
            filterChain.doFilter(request, response);
            return;
        }
        
        //Get authorization header
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        //Check if there is not authorization or token is Bearer type
        if(!(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))){
            filterChain.doFilter(request, response);
            return;
        }

        
    }
    
}
