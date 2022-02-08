package com.serrano.app.helpdesk.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.serrano.app.helpdesk.utils.CustomJWT;
import com.serrano.app.helpdesk.utils.CustomMapMessage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

        try{
            String token = authorizationHeader.substring("Bearer ".length());
            DecodedJWT decoded = CustomJWT.decode(token);
            UsernamePasswordAuthenticationToken authenticationToken = CustomJWT.createAuthenticationToken(decoded);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }catch(Exception ex){
            CustomMapMessage.onError(response, ex.getMessage(), HttpStatus.FORBIDDEN);
        }       
    }
    
}
