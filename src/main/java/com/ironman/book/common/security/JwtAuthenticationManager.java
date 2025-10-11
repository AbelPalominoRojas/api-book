package com.ironman.book.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


// Lombok anotations
@RequiredArgsConstructor

// Spring Stereotype annotation to indicate that this class is a Spring-managed component
@Component
public class JwtAuthenticationManager implements AuthenticationManager {
    private final JwtService jwtService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String username = jwtService.getUsernameFromToken(token);

        if(username == null && !jwtService.validateToken(token)) {
            throw new AuthenticationServiceException("Invalid or expired token");
        }

        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                authentication.getAuthorities()
        );
    }
}
