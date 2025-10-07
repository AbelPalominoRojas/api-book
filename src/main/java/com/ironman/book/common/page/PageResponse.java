package com.ironman.book.common.page;

import lombok.*;

import java.io.Serializable;
import java.util.List;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T>  implements Serializable {
    private List<T> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer numberOfElements;
    private Long totalElements;
    private int totalPages;
}
