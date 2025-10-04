package com.ironman.book.exception;

import lombok.*;

import java.util.List;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private String message;
    private List<ExceptionDetailResponse> details;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ExceptionDetailResponse {
        private String component;
        private String message;
    }
}
