package com.project.webapi.api;

import com.project.webapi.core.data.ApiResponse;
import com.project.webapi.data.dao.Category;
import com.project.webapi.service.CategoryService;
import com.project.webapi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/trigger")
public class TriggerApi {

    private NewsService newsService;
    private CategoryService categoryService;

    @Autowired
    private TriggerApi(NewsService newsService, CategoryService categoryService) {
        this.newsService = newsService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{category}")
    private ApiResponse<String> triggerClient(@PathVariable String category) throws Exception {
        String result = newsService.getNews(category);
        return new ApiResponse<String>(result);
    }

    @PostMapping("/{id}")
    private ApiResponse<Category> getCategory(@PathVariable int id) {
        long startTime = System.nanoTime();
        Category category = categoryService.getById(Long.valueOf(id));
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) + " ms.");
        return new ApiResponse<>(category);
    }


}
