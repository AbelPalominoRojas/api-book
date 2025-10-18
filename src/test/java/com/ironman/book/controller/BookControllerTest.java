package com.ironman.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironman.book.dto.book.BookRequest;
import com.ironman.book.entity.Book;
import com.ironman.book.entity.Publisher;
import com.ironman.book.mock.BookMock;
import com.ironman.book.mock.PublisherMock;
import com.ironman.book.repository.BookRepository;
import com.ironman.book.repository.PublisherRepository;
import com.ironman.book.util.StatusEnum;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockitoBean
    private BookRepository bookRepository;

    @MockitoBean
    private PublisherRepository publisherRepository;

    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Test
    void getBooksSuccess() throws Exception {
        // Arrange
        var expectedBook = BookMock.getBook();
        given(bookRepository.findAll()).willReturn(List.of(expectedBook));

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books");

        ResultActions result = mockMvc.perform(request);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(expectedBook.getId()))
                .andExpect(jsonPath("$[0].title").value(expectedBook.getTitle()))
                .andExpect(jsonPath("$[0].isbn").value(expectedBook.getIsbn()))
                .andExpect(jsonPath("$[0].publisherId").value(expectedBook.getPublisherId()));
    }


    @Test
    void createBookSuccess() throws Exception {
        // Arrange
        BookRequest bookRequest = BookMock.getBookRequest();
        Publisher publisher = PublisherMock.getPublisherById(bookRequest.getPublisherId());

        given(publisherRepository.findById(anyInt())).willReturn(Optional.of(publisher));
        given(bookRepository.save(bookCaptor.capture()))
                .willAnswer(invocation -> {
                    Book book = invocation.getArgument(0);
                    book.setId(1); // Simulate generated ID
                    return book;
                });

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest));


        ResultActions result = mockMvc.perform(request);

        // Assert
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.publisherId").value(bookRequest.getPublisherId()))
                .andExpect(jsonPath("$.status").value(StatusEnum.ENABLED.getValue()));
    }
    
}