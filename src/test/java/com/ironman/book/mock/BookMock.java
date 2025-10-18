package com.ironman.book.mock;

import com.ironman.book.dto.book.BookRequest;
import com.ironman.book.entity.Book;
import com.ironman.book.util.StatusEnum;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class BookMock {

    public static Book getBookById(Integer id) {
        return Book.builder()
                .id(id)
                .title("The Amazing")
                .authors("John Doe")
                .isbn("abc-4567890123")
                .edition("1st")
                .publicationYear(2022)
                .publisherId(1)
                .status(StatusEnum.ENABLED.getValue())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Book getBook() {
        return getBookById(1);
    }

    public static BookRequest getBookRequest() {
        return BookRequest.builder()
                .isbn("isbn")
                .title("title")
                .authors("author")
                .edition("1st")
                .publicationYear(2023)
                .publisherId(1)
                .build();
    }
}
