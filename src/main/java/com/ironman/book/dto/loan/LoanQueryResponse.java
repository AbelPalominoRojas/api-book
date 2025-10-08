package com.ironman.book.dto.loan;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

// Lombok annotations to reduce boilerplate code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanQueryResponse implements Serializable {

    private Integer id;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private Integer loanStatus;
    private Integer borrowerId;
    private Integer userId;
    private LocalDateTime createdAt;
    private Integer status;
    private List<LoanDetailQueryResponse> loanDetails;
}
