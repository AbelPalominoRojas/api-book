package com.ironman.book.service.impl;

import com.ironman.book.dto.BookDetailResponse;
import com.ironman.book.dto.BookOverviewResponse;
import com.ironman.book.dto.BookSummaryResponse;
import com.ironman.book.entity.Book;
import com.ironman.book.mapper.BookMapper;
import com.ironman.book.repository.BookRepository;
import com.ironman.book.service.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Lombok annotation to generate a constructor with required arguments
@RequiredArgsConstructor

// Spring Stereotype
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookSummaryResponse> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toSummaryResponse)
                .toList();
    }

    @Override
    public BookDetailResponse findById(Integer id) {
        Book foundBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        return bookMapper.toDetailResponse(foundBook);
    }
}
