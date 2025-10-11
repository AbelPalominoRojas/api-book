package com.ironman.book.controller;

import com.ironman.book.dto.user.LoginRequest;
import com.ironman.book.dto.user.UserCreateRequest;
import com.ironman.book.dto.user.UserSecurityResponse;
import com.ironman.book.exception.ExceptionResponse;
import com.ironman.book.service.UserService;
import com.ironman.book.util.HttpStatusCode;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Lombok annotations
@RequiredArgsConstructor

// Web Annotations
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;


    // OPenAPI annotations
    @SecurityRequirements(value = {})
    @ApiResponse(
            responseCode = HttpStatusCode.CREATED,
            description = "User created successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = UserSecurityResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data",
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
    ResponseEntity<UserSecurityResponse> create(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(userCreateRequest));
    }


    // OPenAPI annotations
    @SecurityRequirements(value = {})
    @ApiResponse(
            responseCode = HttpStatusCode.OK,
            description = "User logged in successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = UserSecurityResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExceptionResponse.class
                    )
            )
    )
    @ApiResponse(
            responseCode = HttpStatusCode.UNAUTHORIZED,
            description = "Authentication failed",
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
    @PostMapping("login")
    ResponseEntity<UserSecurityResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(loginRequest));
    }
}
