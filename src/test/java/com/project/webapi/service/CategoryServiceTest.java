package com.project.webapi.service;

import com.project.webapi.data.dao.Category;
import com.project.webapi.repository.CategoryRepository;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    RedisTemplate<String, String> redisTemplate;

    @Mock
    ValueOperations valueOperations;

    @Mock
    ValueOperations valueOperations2;

    @Mock
    CategoryRepository repository;

    @Before
    public void initial() {
        categoryService = new CategoryService(repository, redisTemplate);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations, valueOperations2);
        //doNothing().when(valueOperations).set(anyString(), anyString());
    }

    @Test
    public void validateCategory_exceptionTest() {
        Assertions.assertThrows(Exception.class, () -> categoryService.validateCategory(null));
    }

    @Test
    public void validateCategory_getCategoryFromCache() {
        when(valueOperations.get(anyString())).thenReturn(DateTime.now());
        when(valueOperations2.get(anyString())).thenReturn(dummyCategory());
        when(redisTemplate.opsForValue()).thenReturn(valueOperations, valueOperations2);

        Category category = categoryService.validateCategory("sport");
        assertTrue(dummyCategory().getName().equals(category.getName()));

    }

    @Test
    public void validateCategory_categoryNotFound() {
        when(valueOperations.get(anyString())).thenReturn(null);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations, valueOperations2);

        Assertions.assertThrows(Exception.class, () -> categoryService.validateCategory("sport"));

    }

    @Test
    public void validateCategory_categoryWillSetToCache() {
        when(valueOperations.get(anyString())).thenReturn(null);
        //when(valueOperations2.get(anyString())).thenReturn(dummyCategory());
        when(redisTemplate.opsForValue()).thenReturn(valueOperations, valueOperations2);

        when(repository.findByName(anyString())).thenReturn(Arrays.asList(dummyCategory()));
        Category category = categoryService.validateCategory("sport");
        assertTrue(dummyCategory().getName().equals(category.getName()));

    }

    private Category dummyCategory() {
        Category category = new Category();
        category.setName("general");
        return category;
    }

}