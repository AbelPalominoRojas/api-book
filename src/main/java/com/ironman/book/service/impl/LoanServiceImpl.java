package com.ironman.book.service.impl;

import com.ironman.book.dto.loan.LoanQueryResponse;
import com.ironman.book.dto.loan.LoanRequest;
import com.ironman.book.dto.loan.LoanResponse;
import com.ironman.book.entity.Loan;
import com.ironman.book.exception.DataNotFoundException;
import com.ironman.book.mapper.LoanMapper;
import com.ironman.book.repository.LoanRepository;
import com.ironman.book.service.LoanService;
import com.ironman.book.util.LoanReturnedEnum;
import com.ironman.book.util.LoanStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Lombok annotation to generate a constructor with required arguments
@RequiredArgsConstructor

// Spring Stereotype
@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    @Override
    public LoanQueryResponse findById(Integer id) {
        return loanRepository.findById(id)
                .map(loanMapper::toQueryResponse)
                .orElseThrow(() -> loanDataNotFoundException(id));
    }

    @Override
    public LoanResponse create(LoanRequest loanRequest) {
        Loan loan = loanMapper.toEntity(loanRequest);
        loan.setLoanStatus(LoanStatusEnum.ACTIVE_LOAN.getValue());

        loan.getLoanDetails().forEach(loanDetail -> {
            loanDetail.setLoan(loan);
            loanDetail.setIsReturned(LoanReturnedEnum.NOT_RETURNED.getValue());
        });

        Loan savedLoan = loanRepository.save(loan);

        return loanMapper.toResponse(savedLoan);
    }

    @Override
    public LoanResponse update(Integer id, LoanRequest loanRequest) {
        Loan foundLoan = loanRepository.findById(id)
                .orElseThrow(() -> loanDataNotFoundException(id));

            loanMapper.updateEntity(foundLoan, loanRequest);

        foundLoan.getLoanDetails().forEach(loanDetail -> {
            loanDetail.setLoan(foundLoan);
            loanDetail.getId().setLoanId(foundLoan.getId());
        });

        Loan savedLoan = loanRepository.save(foundLoan);

        return loanMapper.toResponse(savedLoan);
    }




    private static DataNotFoundException loanDataNotFoundException(Integer id) {
        return new DataNotFoundException("Loan not found with id: " + id);
    }

}
