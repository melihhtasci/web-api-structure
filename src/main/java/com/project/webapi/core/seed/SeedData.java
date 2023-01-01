package com.project.webapi.core.seed;

import com.project.webapi.core.data.dao.User;
import com.project.webapi.core.service.UserService;
import com.project.webapi.data.dao.Category;
import com.project.webapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SeedData {

    private UserService userService;
    private CategoryService categoryService;

    @Autowired
    public SeedData(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public void createUsers() {
        long count = userService.findAll().size();
        if (count == 0) {
            User user = new User("admin","java","123123");
            userService.add(user);
        }
    }

    public void createCategories() {
        long count = categoryService.findAll().size();
        if (count == 0) {
            List<Category> categories = new ArrayList<Category>(Arrays.asList(
                    new Category("sport", "api category"),
                    new Category("technology", "api category")
            ));
            categoryService.addAll(categories);
        }

    }

    @EventListener(ApplicationReadyEvent.class)
    public void startToSeedData() {
        System.out.println(">>> Hello world, I have just started up for SEED DATA <<<");
        createUsers();
        createCategories();
    }

}
