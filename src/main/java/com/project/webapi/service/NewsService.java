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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NewsService extends BaseService<News, NewsRepository> {

    private CategoryService categoryService;
    private NewsClient newsClient;
    private AuthorService authorService;
    public NewsService(NewsRepository repository, CategoryService categoryService,
                       NewsClient newsClient, AuthorService authorService) {
        super(repository);
        this.categoryService = categoryService;
        this.newsClient = newsClient;
        this.authorService = authorService;
    }

    public String getNews(String category) throws Exception {

        Category categoryEntity = validateCategory(category);

        NewsResponse newsResponse = newsClient.getByCategory(category, NewsClientParameters.API_KEY.toString());

        Article article = newsResponse.getArticles().stream().findFirst().get();

        Author author = authorService.getAuthorByName(article.author);
        // author db de varmı kontrolu. yoksa ekle devam et.
        // yukaridaki yapildi. calistir test et. todo now
        // todo bunu redisten kontrol et.


        add(new News(article.title, categoryEntity, author));
        return "News added successfully";
    }

    private Category validateCategory(String param) throws Exception {
        if (param.isEmpty() || param.isBlank())
            throw new Exception("Category is required");

        Category category = categoryService.getCategoryByName(param).get();

        // todo kategorileri cacheden kontrol et.
        checkIsDatabaseUpToDateByCategory(param);

        return category;
    }

    private void checkIsDatabaseUpToDateByCategory(String category) throws Exception {
        boolean result = this.repository.findAll().stream()
                .filter(news -> news.getCategory().name == category &&
                        news.createdDate.toLocalDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList()).isEmpty();
        if (!result)
            throw new Exception("Bugun bu kategoride haberler cekilmis.");

    }
}
