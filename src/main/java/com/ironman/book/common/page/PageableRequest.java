package com.ironman.book.common.page;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PageableRequest implements Serializable {
    private Integer pageNumber;
    private Integer pageSize;
}
