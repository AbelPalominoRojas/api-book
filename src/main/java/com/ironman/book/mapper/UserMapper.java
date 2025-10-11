package com.ironman.book.mapper;

import com.ironman.book.dto.user.ProfileOverviewResponse;
import com.ironman.book.dto.user.UserCreateRequest;
import com.ironman.book.dto.user.UserSecurityResponse;
import com.ironman.book.entity.UserEntity;
import com.ironman.book.util.StatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {StatusEnum.class}
)
public interface UserMapper {

    ProfileOverviewResponse toProfileOverviewResponse(UserEntity user);

    @Mapping(target = "accessToken", ignore = true)
    UserSecurityResponse toSecurityResponse(UserEntity user);

    UserEntity toEntity(UserCreateRequest userCreateRequest);

}
