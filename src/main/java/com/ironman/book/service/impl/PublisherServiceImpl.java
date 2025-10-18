package com.ironman.book.service.impl;

import com.ironman.book.dto.publisher.PublisherDetailResponse;
import com.ironman.book.dto.publisher.PublisherOverviewResponse;
import com.ironman.book.dto.publisher.PublisherRequest;
import com.ironman.book.dto.publisher.PublisherResponse;
import com.ironman.book.entity.Publisher;
import com.ironman.book.exception.DataNotFoundException;
import com.ironman.book.exception.DataUniqueException;
import com.ironman.book.exception.ExceptionManager;
import com.ironman.book.mapper.PublisherMapper;
import com.ironman.book.repository.PublisherRepository;
import com.ironman.book.service.PublisherService;
import com.ironman.book.util.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ironman.book.util.Constant.PUBLISHER_CODE_EXISTS;
import static com.ironman.book.util.Constant.PUBLISHER_NOT_FOUND_BY_ID;

// Lombok annotations
@RequiredArgsConstructor

// Stereotype Spring annotation
@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;


    @Override
    public List<PublisherOverviewResponse> findAll() {
        try {
            return publisherRepository.findAll()
                    .stream()
                    .map(publisherMapper::toOverviewResponse)
                    .toList();
        } catch (Exception e) {
            throw ExceptionManager.handleException(e);
        }
    }

    @Override
    public PublisherDetailResponse findById(Integer id) {
        try {
            Publisher publisher = getPublisherOrThrow(id);

            return publisherMapper.toDetailResponse(publisher);
        } catch (Exception e) {
            throw ExceptionManager.handleException(e);
        }
    }

    @Override
    public PublisherResponse create(PublisherRequest publisherRequest) {
        try {

            publisherRepository
                    .findByPublisherCode(publisherRequest.getCode())
                    .ifPresent(p -> {
                        throw new DataUniqueException(PUBLISHER_CODE_EXISTS + publisherRequest.getCode());
                    });

            Publisher publisher = publisherMapper.toEntity(publisherRequest);

            Publisher publisherSaved = publisherRepository.save(publisher);

            return publisherMapper.toResponse(publisherSaved);
        } catch (Exception e) {
            throw ExceptionManager.handleException(e);
        }
    }

    @Override
    public PublisherResponse update(Integer id, PublisherRequest publisherRequest) {
        try {
            Publisher publisherFound = getPublisherOrThrow(id);

            publisherMapper.updateEntity(publisherFound, publisherRequest);

            Publisher publisherUpdated = publisherRepository.save(publisherFound);

            return publisherMapper.toResponse(publisherUpdated);
        } catch (Exception e) {
            throw ExceptionManager.handleException(e);
        }
    }

    @Override
    public PublisherResponse deleteById(Integer id) {
        try {
            Publisher publisherFound = getPublisherOrThrow(id);

            publisherFound.setStatus(StatusEnum.DISABLED.getValue());

            Publisher publisherUpdated = publisherRepository.save(publisherFound);

            return publisherMapper.toResponse(publisherUpdated);
        } catch (Exception e) {
            throw ExceptionManager.handleException(e);
        }
    }


    private Publisher getPublisherOrThrow(Integer id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(PUBLISHER_NOT_FOUND_BY_ID + id));
    }
}
