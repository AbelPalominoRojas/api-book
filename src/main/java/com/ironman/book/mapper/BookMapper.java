package com.ironman.book.mapper;

import com.ironman.book.dto.*;
import com.ironman.book.entity.Book;
import com.ironman.book.util.StatusEnum;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
        componentModel = ComponentModel.SPRING,
        imports = {StatusEnum.class},
        uses = {PublisherMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BookMapper {

    BookDetailResponse toDetailResponse(Book book);

    BookOverviewResponse toOverviewResponse(Book book);

    BookSummaryResponse toSummaryResponse(Book book);

    BookResponse toResponse(Book book);

    @Mapping(target = "status", expression = "java(StatusEnum.ENABLED.getValue())")
    Book toEntity(BookRequest request);

    void updateEntity(@MappingTarget Book book, BookRequest request);
}
