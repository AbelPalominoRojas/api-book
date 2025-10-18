package com.ironman.book.service;

import com.ironman.book.dto.publisher.PublisherDetailResponse;
import com.ironman.book.dto.publisher.PublisherOverviewResponse;
import com.ironman.book.dto.publisher.PublisherRequest;
import com.ironman.book.dto.publisher.PublisherResponse;
import com.ironman.book.entity.Publisher;
import com.ironman.book.exception.DataNotFoundException;
import com.ironman.book.exception.DataUniqueException;
import com.ironman.book.exception.DatabaseUnexpectedException;
import com.ironman.book.exception.UnexpectedException;
import com.ironman.book.mapper.PublisherMapperImpl;
import com.ironman.book.mock.PublisherMock;
import com.ironman.book.repository.PublisherRepository;
import com.ironman.book.service.impl.PublisherServiceImpl;
import com.ironman.book.util.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.ironman.book.util.Constant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @Spy
    private PublisherMapperImpl publisherMapper;

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Captor
    private ArgumentCaptor<Publisher> publisherCaptor;

    @Test
    void returnPublishersWhenExist() {
        // Arrange
        Publisher publisher = PublisherMock.getPublisher();

        given(publisherRepository.findAll()).willReturn(List.of(publisher));

        // Act
        List<PublisherOverviewResponse> publishers = publisherService.findAll();

        // Assert
        assertNotNull(publishers);
        assertEquals(1, publishers.size());
        assertEquals(publisher.getId(), publishers.get(0).getId());
        assertEquals(publisher.getPublisherCode(), publishers.get(0).getCode());
        assertEquals(publisher.getPublisherName(), publishers.get(0).getName());
    }

    @Test
    void returnPublisherWhenIdExists() {
        // Arrange
        Integer publisherId = 1;
        Publisher publisher = PublisherMock.getPublisherById(publisherId);

        given(publisherRepository.findById(anyInt())).willReturn(Optional.of(publisher));

        // Act
        PublisherDetailResponse publisherDetail = publisherService.findById(publisherId);

        // Assert
        assertNotNull(publisherDetail);
        assertEquals(publisherId, publisherDetail.getId());
        assertEquals(publisher.getPublisherCode(), publisherDetail.getCode());
        assertEquals(publisher.getPublisherName(), publisherDetail.getName());
        assertEquals(publisher.getStatus(), publisherDetail.getStatus());
        assertEquals(publisher.getCreatedAt(), publisherDetail.getCreatedAt());
    }

    @Test
    void createPublisher() {
        // Arrange
        PublisherRequest publisherRequest = PublisherMock.getPublisherRequest();

        given(publisherRepository.save(publisherCaptor.capture()))
                .willAnswer(invocation -> {
                    Publisher p = invocation.getArgument(0);
                    p.setId(1);
                    p.setCreatedAt(LocalDateTime.now());
                    return p;
                });

        given(publisherRepository.findByPublisherCode(anyString()))
                .willReturn(Optional.empty());

        // Act
        PublisherResponse publisherResponse = publisherService.create(publisherRequest);

        // Assert
        assertNotNull(publisherResponse);
        assertNotNull(publisherResponse.getId());
        assertEquals(publisherRequest.getName(), publisherResponse.getName());
        assertEquals(StatusEnum.ENABLED.getValue(), publisherResponse.getStatus());
    }

    @Test
    void updatePublisherWhenIdExists() {
        // Arrange
        Integer publisherId = 1;
        PublisherRequest publisherRequest = PublisherMock.getPublisherRequest();
        Publisher publisher = PublisherMock.getPublisherById(publisherId);

        given(publisherRepository.findById(anyInt())).willReturn(Optional.of(publisher));
        given(publisherRepository.save(publisherCaptor.capture()))
                .willAnswer(invocation -> invocation.getArgument(0));

        // Act
        PublisherResponse publisherResponse = publisherService.update(publisherId, publisherRequest);

        // Assert
        assertNotNull(publisherResponse);
        assertEquals(publisherId, publisherResponse.getId());
        assertEquals(publisherRequest.getName(), publisherResponse.getName());

    }

    @Test
    void disablePublisherWhenIdExists() {
        // Arrange
        Integer publisherId = 1;
        Publisher publisher = PublisherMock.getPublisherById(publisherId);

        given(publisherRepository.findById(anyInt())).willReturn(Optional.of(publisher));
        given(publisherRepository.save(publisherCaptor.capture()))
                .willAnswer(invocation -> invocation.getArgument(0));

        // Act
        PublisherResponse publisherResponse = publisherService.deleteById(publisherId);

        // Assert
        assertNotNull(publisherResponse);
        assertEquals(publisherId, publisherResponse.getId());
        assertEquals(StatusEnum.DISABLED.getValue(), publisherResponse.getStatus());
    }

    @Test
    void returnErrorWhenPublisherNotFound() {
        // Arrange
        Integer publisherId = 1;
        String expectedMessage = PUBLISHER_NOT_FOUND_BY_ID + publisherId;
        given(publisherRepository.findById(anyInt())).willReturn(Optional.empty());

        // Act & Assert
        var exception = assertThrows(
                DataNotFoundException.class,
                () -> publisherService.findById(publisherId)
        );

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void returnErrorWhenDatabaseErrorOnFindAll() {
        // Arrange
        DataIntegrityViolationException expectedException = new DataIntegrityViolationException("DB error");

        given(publisherRepository.findAll()).willThrow(expectedException);

        // Act & Assert
        var exception = assertThrows(
                DatabaseUnexpectedException.class,
                () -> publisherService.findAll()
        );

        assertEquals(DATABASE_UNEXPECTED_ERROR, exception.getMessage());
    }

    @Test
    void returnErrorWhenUnexpectedErrorOnFindById() {
        // Arrange
        Integer publisherId = 1;
        RuntimeException unexpectedException = new RuntimeException("Unexpected error");

        given(publisherRepository.findById(anyInt())).willThrow(unexpectedException);

        // Act & Assert
        var exception = assertThrows(
                UnexpectedException.class,
                () -> publisherService.findById(publisherId)
        );

        assertEquals(UNEXPECTED_ERROR, exception.getMessage());
    }

    @Test
    void returnErrorWhenDuplicatePublisherCodeOnCreate() {
        // Arrange
        PublisherRequest publisherRequest = PublisherMock.getPublisherRequest();
        String expectedMessage = PUBLISHER_CODE_EXISTS + publisherRequest.getCode();

        given(publisherRepository.findByPublisherCode(anyString()))
                .willReturn(Optional.of(PublisherMock.getPublisher()));

        // Act & Assert
        var exception = assertThrows(
                DataUniqueException.class,
                () -> publisherService.create(publisherRequest)
        );

        assertEquals(expectedMessage, exception.getMessage());
    }

}