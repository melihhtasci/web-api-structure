package com.project.webapi.service;

import com.project.webapi.clients.Article;
import com.project.webapi.clients.NewsClient;
import com.project.webapi.clients.NewsResponse;
import com.project.webapi.data.dao.Author;
import com.project.webapi.data.dao.Category;
import com.project.webapi.repository.NewsRepository;
import com.project.webapi.util.ResponseUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest {

    @InjectMocks
    private NewsService newsService;

    @Mock
    RedisTemplate<String, String> redisTemplate;

    @Mock
    ValueOperations valueOperations;

    @Mock
    NewsClient client;

    @Mock
    CategoryService categoryService;

    @Mock
    AuthorService authorService;

    @Mock
    NewsRepository repository;

    @Before
    public void initial() {
        newsService = new NewsService(repository, categoryService, client, authorService, redisTemplate);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        //doNothing().when(valueOperations).set(anyString(), anyString());
    }

    @Test
    public void pullNews_TriggerTimeAlreadyUpdated_test_mustReturnNewsAlreadyUpdated() {
        when(valueOperations.get(anyString())).thenReturn(DateTime.now());
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        String response = newsService.pullNews();
        assertEquals(ResponseUtils.NEWS_ALREADY_UPDATED.message(), response);
    }

    @Test
    public void pullNews_successfulTest_withDifferentVarietyData() {
        when(valueOperations.get(anyString())).thenReturn(null);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        when(client.get(anyString(), anyString())).thenReturn(dummyNewsResponse());
        when(categoryService.getCategoryByName(anyString())).thenReturn(dummyCategory());

        when(authorService.getAuthorByName(anyString())).thenReturn(dummyAuthor());

        when(newsService.addAll(anyList())).thenReturn(null);

        String response = newsService.pullNews();
        assertEquals(ResponseUtils.NEWS_ADDED.message(), response);
    }

    @Test
    public void pullNewsByCategory_alreadyUpdatedCategory() {
        when(valueOperations.get(anyString())).thenReturn("true");
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        String response = newsService.pullNews("anyString");
        assertEquals(ResponseUtils.CATEGORY_ALREADY_UPDATED.message(), response);
    }

    @Test
    public void pullNewsByCategory_getCurrentNewsByCategory() {
        when(valueOperations.get(anyString())).thenReturn(null);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        when(client.getByCategory(anyString(), anyString())).thenReturn(dummyNewsResponse());

        when(authorService.getAuthorByName(anyString())).thenReturn(dummyAuthor());

        String response = newsService.pullNews("anyString");
        assertEquals(ResponseUtils.NEWS_ADDED.message(), response);
    }



    private NewsResponse dummyNewsResponse() {
        List<Article> articles = new ArrayList<Article>(
                Arrays.asList(new Article("Sky sports", "Sport", "Test article 1"),
                        new Article("", "Fun", "Test article 2"),
                        new Article("CNBC", "", "Test article 3"),
                        new Article("", "", "Test article 4"))
        );
        return NewsResponse.builder()
                .status("200")
                .totalResults(articles.size())
                .articles(articles)
                .build();
    }

    private Category dummyCategory() {
        Category category = new Category();
        category.setName("general");
        return category;
    }

    private Author dummyAuthor() {
        Author author = new Author();
        author.setName("Josh");
        return author;
    }


}