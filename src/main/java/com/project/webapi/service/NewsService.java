package com.project.webapi.service;

import com.project.webapi.clients.Article;
import com.project.webapi.clients.NewsClient;
import com.project.webapi.clients.NewsResponse;
import com.project.webapi.core.service.BaseService;
import com.project.webapi.data.dao.Author;
import com.project.webapi.data.dao.Category;
import com.project.webapi.data.dao.News;
import com.project.webapi.enums.NewsClientParameters;
import com.project.webapi.repository.NewsRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Service
public class NewsService extends BaseService<News, NewsRepository> {

    private CategoryService categoryService;
    private NewsClient newsClient;
    private AuthorService authorService;
    private RedisTemplate redisTemplate;

    public NewsService(NewsRepository repository, CategoryService categoryService,
                       NewsClient newsClient, AuthorService authorService, RedisTemplate redisTemplate) {
        super(repository);
        this.categoryService = categoryService;
        this.newsClient = newsClient;
        this.authorService = authorService;
        this.redisTemplate = redisTemplate;
    }

    public String getNews(String category) throws Exception {

        Object isUpdate = redisTemplate.opsForValue().get("isUpdate");
        if (isUpdate == null || !isUpdate.toString().equals("true")) {
            Category categoryEntity = validateCategory(category);
            NewsResponse newsResponse = newsClient.getByCategory(category, NewsClientParameters.API_KEY.toString());
            Article article = newsResponse.getArticles().stream().findFirst().get();
            Author author = authorService.getAuthorByName(article.author);

            add(new News(article.title, categoryEntity, author));
            return "News added successfully";
        }
        return "This category is up to date.";
    }

    private Category validateCategory(String param) throws Exception {
        if (param.isEmpty() || param.isBlank())
            throw new Exception("Category is required");

        Category category = categoryService.getCategoryByName(param).get();
        checkIsUpdate(category.id);

        return category;
    }

    private void checkIsUpdate(long categoryId) throws Exception {
        boolean result = this.repository.findAll().stream().filter(news ->
                news.getCategory().getId() == categoryId &&
                        news.createdDate.isBefore(LocalDateTime.now())).collect(Collectors.toList()).isEmpty();
        if (!result) {
            System.out.println(">>> This category is up to date.");
            throw new Exception("This category is up to date.");
        } else {
            System.out.println(">>> isUpdate will set as true.");
            redisTemplate.opsForValue().append("isUpdate", "true");
        }

    }
}
