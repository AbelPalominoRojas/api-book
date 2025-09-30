package com.ironman.book.mapper;

import com.ironman.book.dto.*;
import com.ironman.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface BookMapper {

    BookDetailResponse toDetailResponse(Book book);

    BookOverviewResponse toOverviewResponse(Book book);

    BookSummaryResponse toSummaryResponse(Book book);

    BookResponse toResponse(Book book);

    Book toEntity(BookRequest request);

    void updateEntity(@MappingTarget Book book, BookRequest request);
}
