package com.ironman.book.dto.loan;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;
import java.util.List;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest implements Serializable {

    @NotNull(message = "borrowerId must not be null")
    @Positive(message = "borrowerId must be a positive integer")
    private Integer borrowerId;

    @NotNull(message = "userId must not be null")
    @Positive(message = "userId must be a positive integer")
    private Integer userId;

    @Valid
    private List<LoanDetailRequest> loanDetails;
}
