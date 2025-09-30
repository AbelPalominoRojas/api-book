package com.ironman.book.service;

import com.ironman.book.dto.*;

import java.util.List;

public interface BookService {
    List<BookSummaryResponse> findAll();

    BookDetailResponse findById(Integer id);

    BookResponse create(BookRequest bookRequest);

    BookResponse update(Integer id, BookRequest bookRequest);

    BookResponse deleteById(Integer id);

    List<BookOverviewResponse> findAllByPublisherId(Integer publisherId);
}
