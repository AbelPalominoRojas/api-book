package com.ironman.book.dto.book;

import com.ironman.book.common.page.PageSortRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookPageSortFilterQuery extends PageSortRequest implements Serializable {
    private String title;
    private String authors;
    private Integer publicationYear;
    private Integer publisherId;
}
