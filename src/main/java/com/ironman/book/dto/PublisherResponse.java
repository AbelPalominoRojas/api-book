package com.ironman.book.dto;

import lombok.*;

import java.io.Serializable;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherResponse implements Serializable {

    private Integer id;
    private Integer status;
}
