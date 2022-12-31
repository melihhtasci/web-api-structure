package com.project.news.core.mapper;

import com.project.news.core.data.dao.User;
import com.project.news.core.data.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomMapper {
    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);
    User sourceToDestination(UserDto source);
    UserDto destinationToSource(User destination);
}
