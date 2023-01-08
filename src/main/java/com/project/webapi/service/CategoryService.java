package com.project.webapi.service;

import com.project.webapi.core.service.BaseService;
import com.project.webapi.data.dao.Category;
import com.project.webapi.repository.CategoryRepository;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "categoryCache")
public class CategoryService extends BaseService<Category, CategoryRepository> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    @Cacheable(cacheNames = "category-by-name", key = "#name", unless = "#result == null")
    public Optional<Category> getCategoryByName(String name) throws Exception {
        Optional<Category> category = repository.findByName(name).stream().findFirst();
        if (!category.isPresent())
            throw new Exception("Category is wrong");
        return category;
    }

    @Cacheable(cacheNames = "category", key = "#id", unless = "#result == null")
    @Transactional
    @Override
    public Optional<Category> getById(long id) {
        return super.getById(id);
    }

    @Override
    @Cacheable(cacheNames = "categories", unless = "#result == null")
    public List<Category> findAll() {
        return super.findAll();
    }
}
