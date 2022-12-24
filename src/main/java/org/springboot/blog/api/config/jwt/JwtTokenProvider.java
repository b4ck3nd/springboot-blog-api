package org.springboot.blog.api.config.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springboot.blog.api.exception.UnHandledException;
import org.springframework.beans.factory.annotation.Value;import org.hibernate.cfg.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {


    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration}")
    private long jwtExpiration;

    //generate jwt token
    public String generateToken(Authentication authentication) {
        String username=authentication.getName();
        Date expirationDate=new Date(new Date().getTime() + jwtExpiration);
        String token= Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(key())
                .compact();
        return token;
    }

    //get username from jwt token
    public String getUsernameFromToken(String token) {

        Claims claims=Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        //return claims.getSubject();
        String username=claims.getSubject();
        return username;
    }


    //validate jwt token
    public boolean validateToken(String token) throws UnHandledException {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        }
        finally {

        }
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
