package com.project.webapi.core.service.common;

import com.project.webapi.core.data.dao.User;
import com.project.webapi.core.repository.jpa.UserRepository;
import com.project.webapi.core.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserRepository> {

    public UserService(UserRepository repository) {
        super(repository);
    }

}
