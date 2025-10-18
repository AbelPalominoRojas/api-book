package com.ironman.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironman.book.dto.publisher.PublisherRequest;
import com.ironman.book.entity.Publisher;
import com.ironman.book.mock.PublisherMock;
import com.ironman.book.repository.PublisherRepository;
import com.ironman.book.util.StatusEnum;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockitoBean
    private PublisherRepository publisherRepository;

    @Captor
    private ArgumentCaptor<Publisher> publisherCaptor;

    @Test
    void getAllPublishers() throws Exception {
        // Arrange
        Publisher publisher = PublisherMock.getPublisher();

        given(publisherRepository.findAll()).willReturn(List.of(publisher));

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/publishers");

        ResultActions result = mockMvc.perform(request);

        // Assert
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].id").value(publisher.getId()))
                .andExpect(jsonPath("$[0].name").value(publisher.getPublisherName()))
                .andExpect(jsonPath("$[0].code").value(publisher.getPublisherCode()));
    }

    @Test
    void getPublisherById() throws Exception {
        // Arrange
        Integer id = 1;
        Publisher publisher = PublisherMock.getPublisherById(id);

        given(publisherRepository.findById(anyInt())).willReturn(Optional.of(publisher));

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/publishers/" + id);
        ResultActions result = mockMvc.perform(request);

        // Assert
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(publisher.getId()))
                .andExpect(jsonPath("$.name").value(publisher.getPublisherName()))
                .andExpect(jsonPath("$.code").value(publisher.getPublisherCode()))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.status").value(publisher.getStatus()));
    }

    @Test
    void createPublisherSuccess() throws Exception {
        // Arrange
        PublisherRequest publisherRequest = PublisherMock.getPublisherRequest();

        given(publisherRepository.save(publisherCaptor.capture())).willAnswer(invocation -> {
            Publisher savedPublisher = invocation.getArgument(0);
            savedPublisher.setId(1); // Simulate generated ID
            return savedPublisher;
        });

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/publishers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(publisherRequest));

        ResultActions result = mockMvc.perform(request);

        // Assert
        result.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(publisherRequest.getName()))
                .andExpect(jsonPath("$.status").value(StatusEnum.ENABLED.getValue()));
    }


    @Test
    void updatePublisherSuccess() throws Exception {
        // Arrange
        Integer id = 1;
        PublisherRequest publisherRequest = PublisherMock.getPublisherRequest();
        Publisher foundPublisher = PublisherMock.getPublisherById(id);

        given(publisherRepository.findById(anyInt())).willReturn(Optional.of(foundPublisher));
        given(publisherRepository.save(publisherCaptor.capture()))
                .willAnswer(invocation -> invocation.getArgument(0));

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/publishers/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(publisherRequest));

        ResultActions result = mockMvc.perform(request);

        // Assert
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(publisherRequest.getName()));

    }

    @Test
    void deletePublisherSuccess() throws Exception {
        // Arrange
        Integer id = 1;
        Publisher foundPublisher = PublisherMock.getPublisherById(id);

        given(publisherRepository.findById(anyInt())).willReturn(Optional.of(foundPublisher));
        given(publisherRepository.save(publisherCaptor.capture()))
                .willAnswer(invocation -> invocation.getArgument(0));

        // Act
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/publishers/" + id);

        ResultActions result = mockMvc.perform(request);

        // Assert
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(foundPublisher.getPublisherName()))
                .andExpect(jsonPath("$.status").value(StatusEnum.DISABLED.getValue()));

    }

}