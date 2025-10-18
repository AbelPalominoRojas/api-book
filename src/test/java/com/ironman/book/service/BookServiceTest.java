package com.ironman.book.service;

import com.ironman.book.dto.book.BookRequest;
import com.ironman.book.dto.book.BookResponse;
import com.ironman.book.dto.book.BookSummaryResponse;
import com.ironman.book.entity.Book;
import com.ironman.book.mapper.BookMapperImpl;
import com.ironman.book.mapper.PublisherMapperImpl;
import com.ironman.book.mock.BookMock;
import com.ironman.book.mock.PublisherMock;
import com.ironman.book.repository.BookRepository;
import com.ironman.book.service.impl.BookServiceImpl;
import com.ironman.book.util.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PublisherService publisherService;

    @Spy
    private PublisherMapperImpl publisherMapper;

    @Spy
    private BookMapperImpl bookMapperSpy = new BookMapperImpl(publisherMapper);

    @InjectMocks
    private BookServiceImpl bookService;

    @Captor
    private ArgumentCaptor<Book> bookCaptor;


    @Test
    void returnBooksWhenExist() {
        // Arrange
        var expectedBook = BookMock.getBook();
        given(bookRepository.findAll()).willReturn(List.of(expectedBook));


        // Act
        List<BookSummaryResponse> books = bookService.findAll();

        // Assert
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(expectedBook.getId(), books.get(0).getId());
        assertEquals(expectedBook.getTitle(), books.get(0).getTitle());
    }

    @Test
    void returnBookWhenFindById() {
        // Arrange
        Integer bookId = 1;
        var expectedBook = BookMock.getBookById(bookId);

        given(bookRepository.findById(anyInt())).willReturn(Optional.of(expectedBook));

        // Act
        var bookDetailResponse = bookService.findById(bookId);

        // Assert
        assertNotNull(bookDetailResponse);
        assertEquals(bookId, bookDetailResponse.getId());
        assertEquals(expectedBook.getTitle(), bookDetailResponse.getTitle());
        assertEquals(expectedBook.getIsbn(), bookDetailResponse.getIsbn());
        assertEquals(expectedBook.getAuthors(), bookDetailResponse.getAuthors());
        assertEquals(expectedBook.getEdition(), bookDetailResponse.getEdition());
        assertEquals(expectedBook.getPublicationYear(), bookDetailResponse.getPublicationYear());
        assertEquals(expectedBook.getPublisherId(), bookDetailResponse.getPublisherId());
    }

    @Test
    void createBookSuccessfully() {
        // Arrange
        BookRequest bookRequest = BookMock.getBookRequest();

        given(publisherService.findById(anyInt())).willReturn(PublisherMock.getPublisherDetailResponse());

        given(bookRepository.save(bookCaptor.capture()))
                .willAnswer(invocation -> {
                    Book book = invocation.getArgument(0);
                    book.setId(1); // Simulate generated ID
                    return book;
                });

        // Act
        BookResponse bookResponse = bookService.create(bookRequest);

        // Assert
        assertNotNull(bookResponse);
        assertNotNull(bookResponse.getId());
        assertEquals(bookRequest.getPublisherId(), bookResponse.getPublisherId());
        assertEquals(StatusEnum.ENABLED.getValue(), bookResponse.getStatus());
    }

    @Test
    void updateBookSuccessfully() {
        // Arrange
        Integer bookId = 1;
        BookRequest bookRequest = BookMock.getBookRequest();
        Book existingBook = BookMock.getBookById(bookId);

        given(bookRepository.findById(anyInt())).willReturn(Optional.of(existingBook));
        given(publisherService.findById(anyInt())).willReturn(PublisherMock.getPublisherDetailResponse());
        given(bookRepository.save(bookCaptor.capture()))
                .willAnswer(invocation -> invocation.getArgument(0));

        // Act
        BookResponse bookResponse = bookService.update(bookId, bookRequest);

        // Assert
        assertNotNull(bookResponse);
        assertEquals(bookId, bookResponse.getId());
        assertEquals(bookRequest.getPublisherId(), bookResponse.getPublisherId());
    }

    @Test
    void deleteBookSuccessfully() {
        // Arrange
        Integer bookId = 1;
        Book existingBook = BookMock.getBookById(bookId);

        given(bookRepository.findById(anyInt())).willReturn(Optional.of(existingBook));
        given(bookRepository.save(bookCaptor.capture()))
                .willAnswer(invocation -> invocation.getArgument(0));


        // Act
        BookResponse bookResponse = bookService.deleteById(bookId);

        // Assert
        assertNotNull(bookResponse);
        assertEquals(bookId, bookResponse.getId());
        assertEquals(StatusEnum.DISABLED.getValue(), bookResponse.getStatus());

    }


}