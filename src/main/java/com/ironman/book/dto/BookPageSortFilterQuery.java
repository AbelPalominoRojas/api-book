package com.ironman.book.dto;

import lombok.*;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookPageSortFilterQuery implements java.io.Serializable {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortField;
    private String sortOrder;
    private String title;
    private String authors;
    private Integer publicationYear;
    private Integer publisherId;
}
