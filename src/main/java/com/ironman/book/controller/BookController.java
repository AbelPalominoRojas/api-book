package com.ironman.book.controller;

import com.ironman.book.common.page.PageResponse;
import com.ironman.book.dto.book.*;
import com.ironman.book.exception.ExceptionResponse;
import com.ironman.book.service.BookService;
import com.ironman.book.util.HttpStatusCode;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Lombok annotation to generate a constructor with required arguments
@RequiredArgsConstructor

// Spring API annotation
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved list of books",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = BookSummaryResponse.class
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
    ResponseEntity<List<BookSummaryResponse>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.findAll());
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved book details",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = BookDetailResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Book with the given ID not found",
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
    ResponseEntity<BookDetailResponse> findById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.findById(id));
    }


    @ApiResponse(
            responseCode = HttpStatusCode.CREATED,
            description = "Book created successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = BookResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data, please check the request body.",
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
    ResponseEntity<BookResponse> save(@RequestBody BookRequest bookRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.create(bookRequest));
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Book updated successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = BookResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data, please check the request body.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Book with the given ID not found",
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
    ResponseEntity<BookResponse> update(@PathVariable("id") Integer id, @RequestBody BookRequest bookRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.update(id, bookRequest));
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Book deleted successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = BookResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Book with the given ID not found",
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
    ResponseEntity<BookResponse> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.deleteById(id));
    }

    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved list of books by publisher ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = BookOverviewResponse.class
                            )
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
    @GetMapping("/publisher/{publisherId}")
    ResponseEntity<List<BookOverviewResponse>> findAllByPublisherId(@PathVariable("publisherId") Integer publisherId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.findAllByPublisherId(publisherId));
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved list of books matching the provided filters",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = BookOverviewResponse.class
                            )
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid filter parameters, please check your request.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "No books found matching the provided filters.",
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
    @GetMapping("/search")
    ResponseEntity<List<BookOverviewResponse>> findAllByCommonFilters(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "publicationYear", required = false) Integer publicationYear,
            @RequestParam(value = "publisherId", required = false) Integer publisherId
    ) {
        var response = bookService.findAllByCommonFilters(title, author, publicationYear, publisherId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved list of books matching the provided filters",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = BookOverviewResponse.class
                            )
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid filter parameters, please check your request.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "No books found matching the provided filters.",
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
    @GetMapping("/search-query")
    ResponseEntity<List<BookOverviewResponse>> searchUsingQuery(BookFilterQuery filterQuery) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.searchUsingQuery(filterQuery));
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved list of books matching the provided filters",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = BookOverviewResponse.class
                            )
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid filter parameters, please check your request.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "No books found matching the provided filters.",
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
    @GetMapping("/search-native")
    ResponseEntity<List<BookOverviewResponse>> searchUsingNativeQuery(BookFilterQuery filterQuery) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.searchUsingNativeQuery(filterQuery));
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved list of books matching the provided filters",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = BookOverviewResponse.class
                            )
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid filter parameters, please check your request.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "No books found matching the provided filters.",
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
    @GetMapping("/search-spel")
    ResponseEntity<List<BookOverviewResponse>> searchUsingSpEL(BookFilterQuery filterQuery) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.searchUsingSpEL(filterQuery));
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved list of books matching the provided filters",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = BookOverviewResponse.class
                            )
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid filter parameters, please check your request.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "No books found matching the provided filters.",
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
    @GetMapping("/search-projection")
    ResponseEntity<List<BookOverviewResponse>> searchUsingProjection(BookFilterQuery filterQuery) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.searchUsingProjection(filterQuery));
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved paged list of books"
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid pagination parameters, please check your request.",
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
    @GetMapping("/paged")
    ResponseEntity<PageResponse<BookOverviewResponse>> findAllPaged(
            @RequestParam(value = "pageNumber", defaultValue = "1")
            @Min(value = 1, message = "Page number must be at least 1")
            int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        PageResponse<BookOverviewResponse> pagedResult = bookService.findAllPaged(pageNumber, pageSize);
        return ResponseEntity.ok(pagedResult);
    }


    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved paged list of books matching the provided filters"
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid pagination or filter parameters, please check your request.",
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
    @GetMapping("/paged-search")
    ResponseEntity<PageResponse<BookOverviewResponse>> pageSearchUsingProjection(BookPageFilterQuery filterQuery) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.pageSearchUsingProjection(filterQuery));
    }

    // PageResponse<BookOverviewResponse> pageAndSortUsingProjection(BookPageSortFilterQuery filterQuery)
    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successfully retrieved paged and sorted list of books matching the provided filters"
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid pagination, sorting, or filter parameters, please check your request.",
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
    @GetMapping("/paged-sorted-search")
    ResponseEntity<PageResponse<BookOverviewResponse>> pageAndSortUsingProjection(BookPageSortFilterQuery filterQuery) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.pageAndSortUsingProjection(filterQuery));
    }
}
