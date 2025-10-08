package com.ironman.book.service;

import com.ironman.book.dto.loan.LoanQueryResponse;
import com.ironman.book.dto.loan.LoanRequest;
import com.ironman.book.dto.loan.LoanResponse;

public interface LoanService {
    LoanQueryResponse findById(Integer id);

    LoanResponse create(LoanRequest loanRequest);

    LoanResponse update(Integer id, LoanRequest loanRequest);
}
