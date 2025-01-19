package com.example.Rocket.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
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
    private String APP_SECRET;

    @Value("${questapp.expires.in}")
    private long EXPIRES_IN;

    private Key signingKey;

    @PostConstruct
    public void init() {
        if (APP_SECRET == null || APP_SECRET.isEmpty()) {
            throw new IllegalArgumentException("APP_SECRET cannot be null or empty");
        }
        System.out.println("APP_SECRET: " + APP_SECRET);
        signingKey = Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8));

    }

    public String generateJwtToken(Authentication auth) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        System.out.println("User Details: " + userDetails); // Debug
        System.out.println("Signing Key123: " + signingKey);
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(signingKey,SignatureAlgorithm.HS512)
                .compact();

    }

    public String generateJwtTokenByUserId(Long userId) {
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(userId))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, signingKey).compact();
    }

    Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(signingKey).build().parseClaimsJws(token).getBody(); //parse the token and get the body
        return Long.parseLong(claims.getSubject()); //we parse the subject to long and return it
    }
    boolean validateToken(String token) {
        try {
            System.out.println("Starting token validation: " + token);
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);

            // Parsed Claims'i yazdır
            System.out.println("Parsed Claims: " + claimsJws.getBody());
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
        Date expiration = Jwts.parser().setSigningKey(signingKey).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}