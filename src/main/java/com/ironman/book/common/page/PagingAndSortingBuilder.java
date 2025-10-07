package com.ironman.book.common.page;

import static org.springframework.data.domain.Sort.Direction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;

public abstract class PagingAndSortingBuilder {

    public <T, U> PageResponse<U> buildPageResponse(Page<T> page, Function<T, U> converter) {
        List<U> content = page.getContent()
                .stream()
                .map(converter)
                .toList();

        return PageResponse.<U>builder()
                .content(content)
                .pageNumber(page.getNumber() + 1)
                .pageSize(page.getSize())
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    public Pageable buildPageable(PageableRequest pageableRequest) {
        return PageRequest.of(
                pageableRequest.getPageNumber() - 1,
                pageableRequest.getPageSize()
        );
    }

    public Pageable buildPageable(PageSortRequest pageSortRequest, String fieldOrColumn) {
        Direction direction = Direction.fromOptionalString(pageSortRequest.getSortOrder())
                .orElse(Direction.DESC);

        Sort sort = Sort.by(direction, fieldOrColumn);

        return PageRequest.of(
                pageSortRequest.getPageNumber() - 1,
                pageSortRequest.getPageSize(),
                sort
        );
    }
}
