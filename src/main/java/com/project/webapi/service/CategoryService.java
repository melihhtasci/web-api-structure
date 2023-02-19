package com.project.webapi.service;

import com.project.webapi.core.service.BaseService;
import com.project.webapi.data.dao.Category;
import com.project.webapi.repository.CategoryRepository;
import com.project.webapi.util.ResponseUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.Strings;
import org.joda.time.DateTime;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "categoryCache")
@Slf4j
public class CategoryService extends BaseService<Category, CategoryRepository> {

    private RedisTemplate redisTemplate;

    public CategoryService(CategoryRepository repository, RedisTemplate redisTemplate) {
        super(repository);
        this.redisTemplate = redisTemplate;
    }

    @SneakyThrows
    @Cacheable(cacheNames = "category-by-name", key = "#name", unless = "#result == null")
    public Category getCategoryByName(String name) {
        Optional<Category> category = repository.findByName(name).stream().findFirst();
        if (!category.isPresent()) {
            log.warn("There is no category '" + name + "'");
            throw new Exception(ResponseUtils.CATEGORY_NOT_FOUND.message());
        }
        return category.get();
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

    @SneakyThrows
    public Category validateCategory(String param) {
        if (Strings.isNullOrEmpty(param))
            throw new Exception(ResponseUtils.CATEGORY_IS_REQUIRED.message());

        String updateDateKey = param + "-update-date";
        String cacheKey = param + "-update-category";
        DateTime updatedDate = (DateTime) redisTemplate.opsForValue().get(updateDateKey);

        if (updatedDate != null && updatedDate.isAfter(DateTime.now().plusDays(-1)))
            return (Category) redisTemplate.opsForValue().get(cacheKey);

        Category category = getCategoryByName(param);
        redisTemplate.opsForValue().set(cacheKey, category);
        redisTemplate.opsForValue().set(updateDateKey, DateTime.now());
        return category;
    }

}
