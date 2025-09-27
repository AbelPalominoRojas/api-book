package com.ironman.book.controller;

import com.ironman.book.entity.Publisher;
import com.ironman.book.repository.PublisherRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Web Annotations
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    @GetMapping
    List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

}
