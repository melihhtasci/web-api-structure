package com.project.webapi.api;

import com.project.webapi.clients.NewsClient;
import com.project.webapi.clients.NewsResponse;
import com.project.webapi.core.data.ApiResponse;
import com.project.webapi.core.data.dao.User;
import com.project.webapi.core.data.dto.UserDto;
import com.project.webapi.core.mapper.BaseMapper;
import com.project.webapi.core.mapper.UserMapper;
import com.project.webapi.core.service.UserService;
import com.project.webapi.enums.NewsClientParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserApi {

    private UserService userService;
    private NewsClient newsClient;
    private UserMapper mapper;

    @Autowired
    public UserApi(UserService userService, NewsClient newsClient, UserMapper mapper) {
        this.userService = userService;
        this.newsClient = newsClient;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getUsers() {
        NewsResponse response = newsClient.get("tr", NewsClientParameters.API_KEY.toString());
        List<User> users = userService.findAll();
        List<UserDto> userDtos = mapper.toDto(users);
        return ResponseEntity.ok(new ApiResponse<>(userDtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> addUser(@RequestBody UserDto dto) throws Exception {
        User entity = mapper.toEntity(dto);
        userService.add(entity);
        return ResponseEntity.ok(new ApiResponse<>(dto));
    }

}
