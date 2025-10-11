package com.ironman.book.dto.user;

import lombok.*;

import java.io.Serializable;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileOverviewResponse implements Serializable {
    private Integer id;
    private String name;
}
