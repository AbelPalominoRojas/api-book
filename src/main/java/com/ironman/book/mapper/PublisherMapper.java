package com.ironman.book.mapper;

import com.ironman.book.dto.PublisherDetailResponse;
import com.ironman.book.dto.PublisherOverviewResponse;
import com.ironman.book.dto.PublisherRequest;
import com.ironman.book.dto.PublisherResponse;
import com.ironman.book.entity.Publisher;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface PublisherMapper {

    @Mapping(target = "code", source = "publisherCode")
    @Mapping(target = "name", source = "publisherName")
    PublisherOverviewResponse toOverviewResponse(Publisher publisher);

    @Mapping(target = "code", source = "publisherCode")
    @Mapping(target = "name", source = "publisherName")
    PublisherDetailResponse toDetailResponse(Publisher publisher);

    PublisherResponse toResponse(Publisher publisher);

    @Mapping(target = "publisherCode", source = "code")
    @Mapping(target = "publisherName", source = "name")
    Publisher toEntity(PublisherRequest publisherRequest);

    @InheritConfiguration
    void updateEntity(@MappingTarget Publisher publisher, PublisherRequest publisherRequest);
}
