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
    private String APP_SECRET;

    @Value("${questapp.expires.in}")
    private long EXPIRES_IN;

    public String generateJwtTokenByUserId(Long userId) {
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN * 1000); // EXPIRES_IN'i saniyeden milisaniyeye çeviriyoruz
        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}