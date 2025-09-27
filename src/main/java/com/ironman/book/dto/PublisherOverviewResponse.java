package com.ironman.book.dto;

import lombok.*;

import java.io.Serializable;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherOverviewResponse implements Serializable {
    private Integer id;
    private String code;
    private String name;
}
