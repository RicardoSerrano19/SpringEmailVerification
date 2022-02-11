package com.serrano.app.helpdesk.service;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.serrano.app.helpdesk.domain.User;
import com.serrano.app.helpdesk.repository.UserRepository;
import com.serrano.app.helpdesk.utils.CustomJWT;
import com.serrano.app.helpdesk.utils.CustomMapMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService{
    @Autowired
    private UserRepository userRepo;

    @Override
    public void refresh(HttpServletRequest request, HttpServletResponse response){

          //Get authorization header
          String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
          //Check if there is not authorization or token is Bearer type
          if(!(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))){
              throw new RuntimeException("Refresh Token is missing");
          }

          try{
            String refreshToken = authorizationHeader.substring("Bearer ".length());

            DecodedJWT decoded = CustomJWT.decode(refreshToken);
            String email = decoded.getSubject();
            Optional<User> user = userRepo.findByEmail(email);
            if(user.isEmpty()) throw new UsernameNotFoundException(email);
            int minutesToExpire = 5; 
            String accessToken = CustomJWT.create(user.get(), minutesToExpire, request.getRequestURL().toString(), "roles", true);
            CustomMapMessage.onAuthenticationSuccessful(response, accessToken, "bearer", refreshToken, String.valueOf(minutesToExpire * 60000));
          }catch(Exception ex){
              System.out.println("Error");
          }
    }
    
}
