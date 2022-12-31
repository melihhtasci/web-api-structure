package com.project.news.core.seed;

import com.project.news.core.data.dao.User;
import com.project.news.core.repository.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SeedData {

    @Autowired
    private static BaseRepository _userRepository;

    public static void createUsers() {

        long count = _userRepository.count();
        if (count == 0) {
            User user = new User("admin","steve@java.com","123123");
            _userRepository.save(user);
            if (user.id > 0)
                System.out.println("User saved successfully in Seed Method.");
            else
                System.out.println("Seed Method didn't worked well.");
        }

    }

}
