package com.ironman.book.dto;

import lombok.*;

import java.io.Serializable;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherRequest implements Serializable {
    private String code;
    private String name;
}
