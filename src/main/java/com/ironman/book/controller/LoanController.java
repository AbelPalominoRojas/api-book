package com.ironman.book.controller;

import com.ironman.book.dto.loan.LoanQueryResponse;
import com.ironman.book.dto.loan.LoanRequest;
import com.ironman.book.dto.loan.LoanResponse;
import com.ironman.book.exception.ExceptionResponse;
import com.ironman.book.service.LoanService;
import com.ironman.book.util.HttpStatusCode;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Lombok annotation to generate a constructor with required arguments
@RequiredArgsConstructor

// Spring API annotation
@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved loan by ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LoanQueryResponse.class)
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Loan not found with the given ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @GetMapping("/{id}")
    ResponseEntity<LoanQueryResponse> findById(@PathVariable("id") Integer id) {
        LoanQueryResponse response = loanService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    @ApiResponse(
            responseCode = HttpStatusCode.CREATED,
            description = "Successfully created a new loan",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LoanResponse.class)
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @PostMapping
    ResponseEntity<LoanResponse> create(@RequestBody @Valid LoanRequest loanRequest) {
        LoanResponse response = loanService.create(loanRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully updated the loan",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LoanResponse.class)
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Loan not found with the given ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @PutMapping("/{id}")
    ResponseEntity<LoanResponse> update(@PathVariable("id") Integer id, @RequestBody @Valid LoanRequest loanRequest) {
        LoanResponse response = loanService.update(id, loanRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
