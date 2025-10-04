package com.ironman.book.controller.advice;

import static com.ironman.book.exception.ExceptionResponse.ExceptionDetailResponse;

import com.ironman.book.exception.AppBaseException;
import com.ironman.book.exception.DataNotFoundException;
import com.ironman.book.exception.DataUniqueException;
import com.ironman.book.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var details = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> ExceptionDetailResponse.builder()
                        .component(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .toList();

        var response = ExceptionResponse.builder()
                .message("Validation failed")
                .details(details)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AppBaseException.class)
    public ResponseEntity<ExceptionResponse> handleAppBaseException(AppBaseException e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if(e instanceof DataNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (e instanceof DataUniqueException) {
            status = HttpStatus.BAD_REQUEST;
        }

        var response = ExceptionResponse.builder()
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(status)
                .body(response);
    }

}
