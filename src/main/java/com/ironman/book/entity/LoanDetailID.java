package com.ironman.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

// JPA Entity
@Embeddable
public class LoanDetailID implements Serializable {

    @Column(name = "loan_id")
    private Integer loanId;

    @Column(name = "book_id")
    private Integer bookId;

}
