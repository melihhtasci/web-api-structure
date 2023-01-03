package com.project.webapi.core.mapper;

import com.project.webapi.core.data.dao.User;
import com.project.webapi.core.data.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class UserMapper extends BaseMapper<User, UserDto> {

    public User toEntity(UserDto dto) {
        return new User(dto.getName(), dto.getEmail(), dto.getPassword());
    }

    public UserDto toDto(User entity) {
        return new UserDto(entity.getName(), entity.getEmail(), entity.getPassword(), "");
    }

    public List<UserDto> toDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users) {
            userDtos.add(toDto(user));
        }
        return userDtos;
    }

}
