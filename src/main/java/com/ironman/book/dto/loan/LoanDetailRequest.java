package com.ironman.book.dto.loan;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDetailRequest implements Serializable {

    @NotNull(message = "bookId must not be null")
    @Positive(message = "bookId must be a positive integer")
    private Integer bookId;

}
