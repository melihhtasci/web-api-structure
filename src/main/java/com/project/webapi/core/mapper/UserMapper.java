package com.project.webapi.core.mapper;

import com.project.webapi.core.data.dao.User;
import com.project.webapi.core.data.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class UserMapper extends BaseMapper<User, UserDto> {

    @Override
    public User toEntity(UserDto dto) {
        return new User(dto.getName(), dto.getEmail(), dto.getPassword());
    }

    @Override
    public UserDto toDto(User entity) {
        return new UserDto(entity.getName(), entity.getEmail(), entity.getPassword(), "");
    }

    @Override
    public List<UserDto> toDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        users.stream().map(s -> toDto(s)).forEach(s -> userDtos.add(s));
        return userDtos;
    }

    @Override
    public List<User> toEntity(List<UserDto> users) {
        return null;
    }

}
