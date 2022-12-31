package com.project.news.core.service;

import com.project.news.core.data.dao.User;
import com.project.news.core.data.dto.UserDto;
import com.project.news.core.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private BaseRepository repository;

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User addUser(User user) {
        user.createdDate = LocalDate.now();
        repository.save(user);
        return user;
    }

}
