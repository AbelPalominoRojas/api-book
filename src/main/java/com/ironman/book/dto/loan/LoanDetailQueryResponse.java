package com.ironman.book.dto.loan;

import com.ironman.book.dto.book.BookSummaryResponse;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDetailQueryResponse implements Serializable {
    private Integer isReturned;
    private BigDecimal lateFee;
    private BookSummaryResponse book;
}
