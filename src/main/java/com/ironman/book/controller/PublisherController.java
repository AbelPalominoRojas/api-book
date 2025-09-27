package com.ironman.book.controller;

import com.ironman.book.dto.PublisherDetailResponse;
import com.ironman.book.dto.PublisherOverviewResponse;
import com.ironman.book.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
