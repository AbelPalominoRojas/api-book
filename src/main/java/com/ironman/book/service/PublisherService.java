package com.ironman.book.service;

import com.ironman.book.dto.publisher.PublisherDetailResponse;
import com.ironman.book.dto.publisher.PublisherOverviewResponse;
import com.ironman.book.dto.publisher.PublisherRequest;
import com.ironman.book.dto.publisher.PublisherResponse;

import java.util.List;

public interface PublisherService {
    List<PublisherOverviewResponse> findAll();

    PublisherDetailResponse findById(Integer id);

    PublisherResponse create(PublisherRequest publisherRequest);

    PublisherResponse update(Integer id, PublisherRequest publisherRequest);

    PublisherResponse deleteById(Integer id);
}
