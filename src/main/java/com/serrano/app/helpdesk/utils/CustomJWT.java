package com.serrano.app.helpdesk.utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import org.springframework.security.core.userdetails.User;

public class CustomJWT {
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("9a&t+Gg?YC$9cK!E".getBytes());

    public static String create(User user, int minutes, String issuer, String claim){
        Date date = new Date(System.currentTimeMillis() + (minutes * 60000));
        String username = user.getUsername();
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(date)
                .withIssuer(issuer)
                .withClaim(claim, getClaimValues(user))
                .sign(ALGORITHM);
    }

    public static String create(User user, int minutes, String issuer){
        Date date = new Date(System.currentTimeMillis() + (minutes * 60000));
        String username = user.getUsername();
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(date)
                .withIssuer(issuer)
                .sign(ALGORITHM);
    }

    public static DecodedJWT decode(String token){
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        return verifier.verify(token);
    }


    private static List<String> getClaimValues(User user){
        return user.getAuthorities().stream()
            .map(role -> role.getAuthority())
            .collect(Collectors.toList());
    }
}
