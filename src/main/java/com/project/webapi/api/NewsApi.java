package com.project.webapi.api;

import com.project.webapi.core.data.ApiResponse;
import com.project.webapi.core.mapper.BaseMapper;
import com.project.webapi.data.dto.NewsDto;
import com.project.webapi.mapper.NewsMapper;
import com.project.webapi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/news")
public class NewsApi {

    public NewsService newsService;
    public BaseMapper mapper;

    @Autowired
    public NewsApi(NewsService newsService, NewsMapper mapper) {
        this.newsService = newsService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    private ApiResponse<List<NewsDto>> getTop10News() {
        return new ApiResponse<>(mapper.toDto(newsService.getByPaging(1, 10, null)));
    }

    @GetMapping("/get-news")
    private ApiResponse<List<NewsDto>> getNews(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam String columnName) {
        return new ApiResponse<>(mapper.toDto(newsService.getByPaging(page, size, columnName)));
    }
}
