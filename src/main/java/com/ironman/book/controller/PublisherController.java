package com.ironman.book.controller;

import com.ironman.book.dto.PublisherDetailResponse;
import com.ironman.book.dto.PublisherOverviewResponse;
import com.ironman.book.dto.PublisherRequest;
import com.ironman.book.dto.PublisherResponse;
import com.ironman.book.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

// Web Annotations
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;


    @GetMapping
    List<PublisherOverviewResponse> findAll() {
        return publisherService.findAll();
    }

    @GetMapping("/{id}")
    PublisherDetailResponse findById(@PathVariable("id") Integer id) {
        return publisherService.findById(id);
    }

    @PostMapping
    PublisherResponse create(@RequestBody PublisherRequest publisherRequest) {
        return publisherService.create(publisherRequest);
    }

    @PutMapping("/{id}")
    PublisherResponse update(@PathVariable("id") Integer id, @RequestBody PublisherRequest publisherRequest) {
        return publisherService.update(id, publisherRequest);
    }

    @DeleteMapping("/{id}")
    PublisherResponse deleteById(@PathVariable("id") Integer id) {
        return publisherService.deleteById(id);
    }

}
