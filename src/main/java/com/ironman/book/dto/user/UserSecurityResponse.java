package com.ironman.book.dto.user;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSecurityResponse implements Serializable {
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private ProfileOverviewResponse profile;
    private LocalDateTime createdAt;
    private Integer status;
    private String accessToken;
}
