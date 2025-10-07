package com.ironman.book.service;

import com.ironman.book.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<BookSummaryResponse> findAll();

    BookDetailResponse findById(Integer id);

    BookResponse create(BookRequest bookRequest);

    BookResponse update(Integer id, BookRequest bookRequest);

    BookResponse deleteById(Integer id);

    List<BookOverviewResponse> findAllByPublisherId(Integer publisherId);

    List<BookOverviewResponse> findAllByCommonFilters(
            String title,
            String author,
            Integer publicationYear,
            Integer publisherId
    );

    List<BookOverviewResponse> searchUsingQuery(BookFilterQuery filterQuery);

    List<BookOverviewResponse> searchUsingNativeQuery(BookFilterQuery filterQuery);

    List<BookOverviewResponse> searchUsingSpEL(BookFilterQuery filterQuery);

    List<BookOverviewResponse> searchUsingProjection(BookFilterQuery filterQuery);

    Page<BookOverviewResponse> findAllPaged(int page, int size);
}
