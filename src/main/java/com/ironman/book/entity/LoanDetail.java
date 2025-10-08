package com.ironman.book.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

// JPA Entity
@Entity
@Table(name = "loan_details")
public class LoanDetail {

    @EmbeddedId
    private LoanDetailID id;

    @Column(name = "is_returned", nullable = false)
    private Integer isReturned; // {0=not_returned, 1=returned}

    @Column(name = "late_fee", nullable = false)
    private BigDecimal lateFee;

    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @MapsId("loanId")
    @JoinColumn(name = "loan_id", insertable = false, updatable = false)
    private Loan loan;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, insertable = false)
    private String createdAt;

    @Column(name = "status", nullable = false)
    private Integer status;

}
