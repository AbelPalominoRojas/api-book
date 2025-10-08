package com.ironman.book.mapper;

import com.ironman.book.dto.loan.*;
import com.ironman.book.entity.Loan;
import com.ironman.book.entity.LoanDetail;
import com.ironman.book.util.StatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {BookMapper.class},
        imports = {StatusEnum.class}
)
public interface LoanMapper {

    LoanResponse toResponse(Loan loan);

    LoanQueryResponse toQueryResponse(Loan loan);

    @Mapping(target = "status", expression = "java(StatusEnum.ENABLED.getValue())")
    Loan toEntity(LoanRequest loanRequest);

    void updateEntity(@MappingTarget Loan loan, LoanRequest loanRequest);


    LoanDetailQueryResponse toDetailQueryResponse(LoanDetail loanDetail);

    @Mapping(target = "status", expression = "java(StatusEnum.ENABLED.getValue())")
    @Mapping(target = "id.bookId", source = "bookId")
    LoanDetail toEntity(LoanDetailRequest loanDetailRequest);

    @Mapping(target = "id.bookId", source = "bookId")
    void updateEntity(@MappingTarget LoanDetail loanDetail, LoanDetailRequest loanDetailRequest);
}
