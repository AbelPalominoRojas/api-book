package com.ironman.book.service.impl;

import com.ironman.book.dto.*;
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
        Book foundBook = getBookOrElseThrow(id);

        return bookMapper.toDetailResponse(foundBook);
    }

    @Override
    public BookResponse create(BookRequest bookRequest) {
        Book book = bookMapper.toEntity(bookRequest);
        book.setStatus(1);

        Book savedBook = bookRepository.save(book);

        return bookMapper.toResponse(savedBook);
    }

    @Override
    public BookResponse update(Integer id, BookRequest bookRequest) {
        Book foundBook = getBookOrElseThrow(id);

        bookMapper.updateEntity(foundBook, bookRequest);

        Book updatedBook = bookRepository.save(foundBook);

        return bookMapper.toResponse(updatedBook);
    }

    @Override
    public BookResponse deleteById(Integer id) {
        Book foundBook = getBookOrElseThrow(id);
        foundBook.setStatus(0);

        Book updatedBook = bookRepository.save(foundBook);

        return bookMapper.toResponse(updatedBook);
    }

    @Override
    public List<BookOverviewResponse> findAllByPublisherId(Integer publisherId) {
        return bookRepository.findAllByPublisherId(publisherId)
                .stream()
                .map(bookMapper::toOverviewResponse)
                .toList();
    }

    @Override
    public List<BookOverviewResponse> findAllByCommonFilters(
            String title,
            String author,
            Integer publicationYear,
            Integer publisherId
    ) {
        return bookRepository.findAllByTitleLikeAndAuthorsLikeAndPublicationYearAndPublisherId(
                        "%" + title + "%",
                        "%" + author + "%",
                        publicationYear,
                        publisherId
                )
                .stream()
                .map(bookMapper::toOverviewResponse)
                .toList();
    }

    @Override
    public List<BookOverviewResponse> searchUsingQuery(BookFilterQuery filterQuery) {
        return bookRepository.searchUsingQuery(
                        filterQuery.getTitle(),
                        filterQuery.getAuthors(),
                        filterQuery.getPublicationYear(),
                        filterQuery.getPublisherId()
                )
                .stream()
                .map(bookMapper::toOverviewResponse)
                .toList();
    }

    @Override
    public List<BookOverviewResponse> searchUsingNativeQuery(BookFilterQuery filterQuery) {
        return bookRepository.searchUsingNativeQuery(
                        filterQuery.getTitle(),
                        filterQuery.getAuthors(),
                        filterQuery.getPublicationYear(),
                        filterQuery.getPublisherId()
                )
                .stream()
                .map(bookMapper::toOverviewResponse)
                .toList();
    }


    private Book getBookOrElseThrow(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }
}
