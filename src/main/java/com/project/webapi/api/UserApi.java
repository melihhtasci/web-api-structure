package com.project.webapi.api;

import com.project.webapi.core.data.ApiResponse;
import com.project.webapi.core.data.dao.User;
import com.project.webapi.core.data.dto.UserDto;
import com.project.webapi.core.mapper.UserMapper;
import com.project.webapi.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserApi {

    private UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtos = UserMapper.toDto(users);
        return ResponseEntity.ok(new ApiResponse<>(userDtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> addUser(@RequestBody UserDto dto) throws Exception {
        User entity = UserMapper.toEntity(dto);
        userService.add(entity);
        return ResponseEntity.ok(new ApiResponse<>(dto));
    }

}
