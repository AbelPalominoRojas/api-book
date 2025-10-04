package com.ironman.book.controller;

import com.ironman.book.dto.PublisherDetailResponse;
import com.ironman.book.dto.PublisherOverviewResponse;
import com.ironman.book.dto.PublisherRequest;
import com.ironman.book.dto.PublisherResponse;
import com.ironman.book.exception.ExceptionResponse;
import com.ironman.book.service.PublisherService;
import com.ironman.book.util.HttpStatusCode;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

// Web Annotations
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved list of publishers",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = PublisherOverviewResponse.class
                            )
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "An unexpected error occurred, please try again later.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @GetMapping
    ResponseEntity<List<PublisherOverviewResponse>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(publisherService.findAll());
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved publisher details",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = PublisherDetailResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Publisher with the specified ID was not found.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "An unexpected error occurred, please try again later.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @GetMapping("/{id}")
    ResponseEntity<PublisherDetailResponse> findById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(publisherService.findById(id));
    }

    @ApiResponse(
            responseCode = HttpStatusCode.CREATED,
            description = "Publisher created successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = PublisherResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data provided.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "An unexpected error occurred, please try again later.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @PostMapping
    ResponseEntity<PublisherResponse> create(@Valid @RequestBody PublisherRequest publisherRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(publisherService.create(publisherRequest));
    }

    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Publisher updated successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = PublisherResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data provided.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Publisher with the specified ID was not found.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "An unexpected error occurred, please try again later.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @PutMapping("/{id}")
    ResponseEntity<PublisherResponse> update(@PathVariable("id") Integer id, @Valid @RequestBody PublisherRequest publisherRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(publisherService.update(id, publisherRequest));
    }

    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Publisher deleted successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = PublisherResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Publisher with the specified ID was not found.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "An unexpected error occurred, please try again later.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @DeleteMapping("/{id}")
    ResponseEntity<PublisherResponse> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(publisherService.deleteById(id));
    }

}
