package com.ironman.book.controller;

import com.ironman.book.dto.BookDetailResponse;
import com.ironman.book.dto.BookSummaryResponse;
import com.ironman.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Lombok annotation to generate a constructor with required arguments
@RequiredArgsConstructor

// Spring API annotation
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;


    @GetMapping
    List<BookSummaryResponse> findAll() {
        return bookService.findAll();
    }


    @GetMapping("/{id}")
    BookDetailResponse findById(@PathVariable("id") Integer id) {
        return bookService.findById(id);
    }
}
