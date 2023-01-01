package com.project.webapi.core.mapper;

import com.project.webapi.core.data.dao.User;
import com.project.webapi.core.data.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User toEntity(UserDto dto) {
        return new User(dto.getName(), dto.getEmail(), dto.getPassword());
    }
    public static UserDto toDto(User entity) {
        return new UserDto(entity.getName(), entity.getEmail(), entity.getPassword(), "");
    }

    public static List<UserDto> toDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users) {
            userDtos.add(toDto(user));
        }
        return userDtos;
    }

}
