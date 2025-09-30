package com.ironman.book.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest implements Serializable {
    private String isbn;
    private String title;
    private String authors;
    private String edition;
    private Integer publicationYear;
    private Integer publisherId;
}
