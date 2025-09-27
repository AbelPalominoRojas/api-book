package com.ironman.book.service;

import com.ironman.book.dto.PublisherDetailResponse;
import com.ironman.book.dto.PublisherOverviewResponse;
import com.ironman.book.dto.PublisherRequest;
import com.ironman.book.dto.PublisherResponse;

import java.util.List;

public interface PublisherService {
    List<PublisherOverviewResponse> findAll();

    PublisherDetailResponse findById(Integer id);

    PublisherResponse create(PublisherRequest publisherRequest);

    PublisherResponse update(Integer id, PublisherRequest publisherRequest);

    PublisherResponse deleteById(Integer id);
}
