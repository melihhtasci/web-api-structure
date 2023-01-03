package com.project.webapi.service;

import com.project.webapi.core.service.BaseService;
import com.project.webapi.data.dao.Category;
import com.project.webapi.repository.CategoryRepository;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "categoryCache")
public class CategoryService extends BaseService<Category, CategoryRepository> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    public Optional<Category> getCategoryByName(String name) throws Exception {
        Optional<Category> category = repository.findByName(name).stream().findFirst();
        if (!category.isPresent())
            throw new Exception("Category is wrong");
        return category;
    }

    @SneakyThrows({InterruptedException.class})
    @Cacheable(cacheNames = "category", key = "#id", unless = "#result == null")
    @Override
    public Category getById(long id) {
        long startTime = System.nanoTime();
        Thread.sleep(500);
        Category category = super.getById(id);
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) + " ms.");
        return category;
    }
}
