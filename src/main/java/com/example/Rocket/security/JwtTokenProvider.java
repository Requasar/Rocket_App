package com.example.Rocket.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${questapp.app.secret}")
    private String APP_SECRET; //we will crate our token with this secret, key

    @Value("${questapp.expires.in}")
    private long EXPIRES_IN; //token will expire in this time in seconds

   // Key signingKey = Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8));


    public String generateJwtToken(Authentication auth){
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal(); //principal is the user object which we authenticate
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET) //Choosing an algorithm to create token, key
                .compact();
    }

    Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getBody(); //parse the token and get the body
        return Long.parseLong(claims.getSubject()); //we parse the subject to long and return it
    }

    boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token); // if we can parse the token, it is valid because we created it
            return !isTokenExpired(token);
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token");
            return false;
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty.");
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
