package com.ironman.book.dto.user;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest implements Serializable {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
