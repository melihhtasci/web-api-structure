package com.project.webapi.service;

import com.project.webapi.core.service.BaseService;
import com.project.webapi.data.dao.Category;
import com.project.webapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, CategoryRepository> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

}
