package com.ironman.book.controller;

import com.ironman.book.dto.*;
import com.ironman.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    BookResponse save(@RequestBody BookRequest bookRequest) {
        return bookService.create(bookRequest);
    }

    @PutMapping("/{id}")
    BookResponse update(@PathVariable("id") Integer id, @RequestBody BookRequest bookRequest) {
        return bookService.update(id, bookRequest);
    }

    @DeleteMapping("/{id}")
    BookResponse deleteById(@PathVariable("id") Integer id) {
        return bookService.deleteById(id);
    }

    @GetMapping("/publisher/{publisherId}")
    List<BookOverviewResponse> findAllByPublisherId(@PathVariable("publisherId") Integer publisherId) {
        return bookService.findAllByPublisherId(publisherId);
    }

    @GetMapping("/search")
    List<BookOverviewResponse> findAllByCommonFilters(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "publicationYear", required = false) Integer publicationYear,
            @RequestParam(value = "publisherId", required = false) Integer publisherId
    ) {
        return bookService.findAllByCommonFilters(title, author, publicationYear, publisherId);
    }

    @GetMapping("/search-query")
    List<BookOverviewResponse> searchUsingQuery(BookFilterQuery filterQuery) {
        return bookService.searchUsingQuery(filterQuery);
    }

    @GetMapping("/search-native")
    List<BookOverviewResponse> searchUsingNativeQuery(BookFilterQuery filterQuery) {
        return bookService.searchUsingNativeQuery(filterQuery);
    }

    @GetMapping("/search-spel")
    List<BookOverviewResponse> searchUsingSpEL(BookFilterQuery filterQuery) {
        return bookService.searchUsingSpEL(filterQuery);
    }

    @GetMapping("/search-projection")
    List<BookOverviewResponse> searchUsingProjection(BookFilterQuery filterQuery) {
        return bookService.searchUsingProjection(filterQuery);
    }

    @GetMapping("/paged")
    ResponseEntity<Page<BookOverviewResponse>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<BookOverviewResponse> pagedResult = bookService.findAllPaged(page, size);
        return ResponseEntity.ok(pagedResult);
    }

}
