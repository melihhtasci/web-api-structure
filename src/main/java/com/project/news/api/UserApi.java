package com.project.news.api;

import com.project.news.core.data.dao.User;
import com.project.news.core.data.dto.UserDto;
import com.project.news.core.mapper.CustomMapper;
import com.project.news.core.service.UserService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {

        List<User> users = userService.getUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users) {
            userDtos.add(CustomMapper.INSTANCE.destinationToSource(user));
        }

        return ResponseEntity.ok(userDtos);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) {
        User newUser = new User("test", "test", "aaa");
        userService.addUser(newUser);
        if (newUser.id > 0)
            System.out.println("Insert was successfully.");
        return ResponseEntity.ok(dto);
    }

}
