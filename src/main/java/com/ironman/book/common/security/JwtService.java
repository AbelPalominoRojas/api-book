package com.ironman.book.common.security;

import com.ironman.book.common.security.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

// Lombok anotations
@RequiredArgsConstructor

// Spring Stereotype annotation to indicate that this class is a Spring-managed component
@Component
public class JwtService {
    private final JwtProperties jwtProperties;

    public String generateToken(UserDetails user) {
        // Add Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUsername());
        claims.put("authorities", user.getAuthorities());

        Date createdAt = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdAt);
        calendar.add(Calendar.MINUTE, jwtProperties.getExpirationMinutes());

        SecretKey secretKey = getSecretKey();

        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(createdAt)
                .expiration(calendar.getTime())
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();

    }

    public Claims getClaimsFromToken(String token) {
        SecretKey secretKey = getSecretKey();

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
