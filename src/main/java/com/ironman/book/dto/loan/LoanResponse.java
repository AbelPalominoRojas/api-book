package com.ironman.book.dto.loan;

import lombok.*;

import java.io.Serializable;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponse implements Serializable {
    private Integer id;
    private Integer borrowerId;
    private Integer status;
}
