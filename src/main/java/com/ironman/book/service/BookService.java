package com.ironman.book.service;

import com.ironman.book.dto.BookDetailResponse;
import com.ironman.book.dto.BookOverviewResponse;
import com.ironman.book.dto.BookSummaryResponse;

import java.util.List;

public interface BookService {
    List<BookSummaryResponse> findAll();

    BookDetailResponse findById(Integer id);

}
