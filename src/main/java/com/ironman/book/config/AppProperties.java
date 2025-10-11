package com.ironman.book.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// Lombok annotation to generate a constructor with required arguments
@Getter

// Spring Stereotype
@Configuration
public class AppProperties {

    @Value("${app.profile-default}")
    private Integer profileDefault;
}
