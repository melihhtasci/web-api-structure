package com.project.webapi.core.service;

import com.project.webapi.core.data.dao.User;
import com.project.webapi.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService extends BaseService<User, UserRepository> {

    public UserService(UserRepository repository) {
        super(repository);
    }

}
