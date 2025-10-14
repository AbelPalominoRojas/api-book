package com.ironman.book.mock;

import com.ironman.book.dto.publisher.PublisherRequest;
import com.ironman.book.entity.Publisher;
import com.ironman.book.util.StatusEnum;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PublisherMock {

    public static Publisher getPublisherById(Integer id) {
        return Publisher.builder()
                .id(id)
                .publisherCode("PUB001")
                .publisherName("Publisher One")
                .status(StatusEnum.ENABLED.getValue())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Publisher getPublisher() {
        return getPublisherById(1);
    }

    public static PublisherRequest getPublisherRequest() {
        return PublisherRequest.builder()
                .code("MARV001")
                .name("Marvel Comics")
                .build();
    }
}
