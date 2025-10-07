package com.ironman.book.dto;

import com.ironman.book.common.page.PageableRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookPageFilterQuery extends PageableRequest implements Serializable {
    private String title;
    private String authors;
    private Integer publicationYear;
    private Integer publisherId;
}
