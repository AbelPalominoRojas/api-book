package com.ironman.book.dto;

import lombok.*;

import java.io.Serializable;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookPageFilterQuery implements Serializable {
    private Integer pageNumber;
    private Integer pageSize;
    private String title;
    private String authors;
    private Integer publicationYear;
    private Integer publisherId;
}
