package com.project.webapi.service;

import com.project.webapi.clients.Article;
import com.project.webapi.clients.NewsClient;
import com.project.webapi.clients.NewsResponse;
import com.project.webapi.core.mapper.BaseMapper;
import com.project.webapi.core.service.BaseService;
import com.project.webapi.data.dao.Author;
import com.project.webapi.data.dao.Category;
import com.project.webapi.data.dao.News;
import com.project.webapi.enums.NewsClientParameters;
import com.project.webapi.mapper.NewsMapper;
import com.project.webapi.repository.NewsRepository;
import com.project.webapi.util.ResponseUtils;
import lombok.SneakyThrows;
import org.elasticsearch.common.Strings;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NewsService extends BaseService<News, NewsRepository> {

    private final CategoryService categoryService;
    private final NewsClient newsClient;
    private final AuthorService authorService;
    private final RedisTemplate redisTemplate;

    @Autowired
    public NewsService(NewsRepository repository, CategoryService categoryService,
                       NewsClient newsClient, AuthorService authorService,
                       RedisTemplate redisTemplate) {
        super(repository);
        this.categoryService = categoryService;
        this.newsClient = newsClient;
        this.authorService = authorService;
        this.redisTemplate = redisTemplate;
    }

    public String pullNews() {
        DateTime triggerTime = (DateTime) redisTemplate.opsForValue().get("triggerTime");
        if (triggerTime == null || DateTime.now().isAfter(triggerTime.plusHours(1))) {
            NewsResponse response = newsClient.get("tr", NewsClientParameters.API_KEY.toString());

            List<News> newsList = new ArrayList<>();
            response.getArticles().stream().forEach(article -> {
                Category category = categoryService.getCategoryByName("general");
                if (!Strings.isNullOrEmpty(article.getCategory()))
                    category = categoryService.getCategoryByName(article.getCategory());

                Author author = null;
                if (!Strings.isNullOrEmpty(article.getAuthor()))
                    author = authorService.getAuthorByName(article.getAuthor());

                newsList.add(new News(article.getTitle(), category, author));
            });
            addAll(newsList);

            redisTemplate.opsForValue().append("triggerTime", DateTime.now().toString());
            return ResponseUtils.NEWS_ADDED.message();
        }
        return ResponseUtils.NEWS_ALREADY_UPDATED.message();
    }

    @SneakyThrows
    public String pullNews(String category) {

        Object isUpdate = redisTemplate.opsForValue().get("isUpdate");
        if (isUpdate == null || !isUpdate.toString().equals("true")) {
            Category categoryEntity = categoryService.validateCategory(category);
            NewsResponse newsResponse = newsClient.getByCategory(category, NewsClientParameters.API_KEY.toString());
            Article article = newsResponse.getArticles().stream().findFirst().get();
            Author author = authorService.getAuthorByName(article.author);

            add(new News(article.title, categoryEntity, author));
            return ResponseUtils.NEWS_ADDED.message();
        }
        return ResponseUtils.CATEGORY_ALREADY_UPDATED.message();
    }

}
