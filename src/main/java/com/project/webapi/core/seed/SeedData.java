package com.project.webapi.core.seed;

import com.project.webapi.clients.NewsClient;
import com.project.webapi.core.data.dao.User;
import com.project.webapi.core.service.UserService;
import com.project.webapi.data.dao.Category;
import com.project.webapi.data.dao.Language;
import com.project.webapi.service.CategoryService;
import com.project.webapi.service.LanguageService;
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
    private LanguageService languageService;
    private NewsClient newsClient;
    @Autowired
    public SeedData(UserService userService, CategoryService categoryService,
                    LanguageService languageService, NewsClient newsClient) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.languageService = languageService;
        this.newsClient = newsClient;
    }

    public void createUsers() {
        long count = userService.findAll().size();
        if (count == 0) {
            User user = new User("admin","java","123123");
            userService.add(user);
        }
    }

    public void createCategories() {
        // business entertainment general health science sports technology
        long count = categoryService.findAll().size();
        if (count == 0) {
            List<Category> categories = new ArrayList<Category>(Arrays.asList(
                    new Category("sports", "api category"),
                    new Category("business", "api category"),
                    new Category("entertainment", "api category"),
                    new Category("general", "api category"),
                    new Category("health", "api category"),
                    new Category("science", "api category"),
                    new Category("technology", "api category")
            ));
            categoryService.addAll(categories);
        }

    }

    private void createLanguages() {
        long count = languageService.findAll().size();
        if (count == 0) {
            List<Language> languages = new ArrayList<>(Arrays.asList(
                    new Language("tr"),
                    new Language("en")
            ));
            languageService.addAll(languages);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startToSeedData() {
        System.out.println(">>> Hello world, I have just started up for SEED DATA <<<");
        createUsers();
        createCategories();
        createLanguages();
        /*NewsResponse newsResponse = newsClientService.getByCategory("tr", "sports", NewsClientParameters.API_KEY.toString());
        System.out.println(newsResponse.getStatus());*/
    }



}
