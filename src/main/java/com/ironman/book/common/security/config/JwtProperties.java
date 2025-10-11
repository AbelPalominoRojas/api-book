package com.ironman.book.common.security.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// Lombok annotations
@Getter

// Spring Stereotype annotation to indicate that this class contains configuration settings
@Configuration
public class JwtProperties {

    @Value("${jwt.secret-key}")
    private  String secretKey;

    @Value("${jwt.expiration-minutes}")
    private  Integer expirationMinutes;
}
