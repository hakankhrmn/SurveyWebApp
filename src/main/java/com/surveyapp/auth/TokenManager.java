package com.surveyapp.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManager {

    private static final int validity = 15*60*1000;
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String userMail){

        return Jwts.builder()
                .setSubject(userMail)
                .setIssuer("kahraman")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(key)
                .compact();
    }

    boolean tokenValidate(String token){
        if(getUserMailToken(token) != null && isExpired(token)){
            return true;
        }
        return false;
    }

    public String getUserMailToken(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
