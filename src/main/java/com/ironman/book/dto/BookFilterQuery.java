package com.ironman.book.dto;

import lombok.*;

import java.io.Serializable;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookFilterQuery implements Serializable {
    private String title;
    private String authors;
    private Integer publicationYear;
    private Integer publisherId;
}
