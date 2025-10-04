package com.ironman.book.service.impl;

import com.ironman.book.dto.PublisherDetailResponse;
import com.ironman.book.dto.PublisherOverviewResponse;
import com.ironman.book.dto.PublisherRequest;
import com.ironman.book.dto.PublisherResponse;
import com.ironman.book.entity.Publisher;
import com.ironman.book.exception.DataNotFoundException;
import com.ironman.book.exception.DataUniqueException;
import com.ironman.book.mapper.PublisherMapper;
import com.ironman.book.repository.PublisherRepository;
import com.ironman.book.service.PublisherService;
import com.ironman.book.util.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Lombok annotations
@RequiredArgsConstructor

// Stereotype Spring annotation
@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;


    @Override
    public List<PublisherOverviewResponse> findAll() {
        return publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toOverviewResponse)
                .toList();
    }

    @Override
    public PublisherDetailResponse findById(Integer id) {
        Publisher publisher = getPublisherOrThrow(id);

        return publisherMapper.toDetailResponse(publisher);
    }

    @Override
    public PublisherResponse create(PublisherRequest publisherRequest) {
        publisherRepository
                .findByPublisherCode(publisherRequest.getCode())
                .ifPresent(p -> {
                    throw new DataUniqueException("Publisher code already exists: " + publisherRequest.getCode());
                });

        Publisher publisher = publisherMapper.toEntity(publisherRequest);

        Publisher publisherSaved = publisherRepository.save(publisher);

        return publisherMapper.toResponse(publisherSaved);
    }

    @Override
    public PublisherResponse update(Integer id, PublisherRequest publisherRequest) {
        Publisher publisherFound = getPublisherOrThrow(id);

        publisherMapper.updateEntity(publisherFound, publisherRequest);

        Publisher publisherUpdated = publisherRepository.save(publisherFound);

        return publisherMapper.toResponse(publisherUpdated);
    }

    @Override
    public PublisherResponse deleteById(Integer id) {

        Publisher publisherFound = getPublisherOrThrow(id);

        publisherFound.setStatus(StatusEnum.DISABLED.getValue());

        Publisher publisherUpdated = publisherRepository.save(publisherFound);

        return publisherMapper.toResponse(publisherUpdated);
    }


    private Publisher getPublisherOrThrow(Integer id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Publisher not found with id: " + id));
    }
}
