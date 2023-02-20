package com.project.webapi.api;

import com.project.webapi.core.data.ApiResponse;
import com.project.webapi.core.data.dao.FlowLog;
import com.project.webapi.core.service.es.FlowLogElasticService;
import com.project.webapi.service.CategoryService;
import com.project.webapi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trigger")
public class TriggerApi {

    private NewsService newsService;
    private CategoryService categoryService;
    private FlowLogElasticService flowLogElasticService;
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private TriggerApi(NewsService newsService, CategoryService categoryService,
                       FlowLogElasticService flowLogElasticService, ElasticsearchOperations elasticsearchOperations) {
        this.newsService = newsService;
        this.categoryService = categoryService;
        this.flowLogElasticService = flowLogElasticService;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @GetMapping("/")
    private ApiResponse<String> triggerNews() throws Exception {
        String response = newsService.pullNews();
        return new ApiResponse<String>(response);
    }

    @GetMapping("/{category}")
    private ApiResponse<String> triggerClient(@PathVariable String category) throws Exception {
        String result = newsService.pullNews(category);
        return new ApiResponse<String>(result);
    }

    @PostMapping("/{word}")
    private ApiResponse<String> test(@PathVariable String word) {
        System.out.println("It was test.");
        return new ApiResponse<String>(word);
    }

    @GetMapping("/find-by-type/{type}")
    private ApiResponse<List<FlowLog>> findByType(@PathVariable String type) {
        Page<FlowLog> flowLogPage = flowLogElasticService.repository.findByType(type, PageRequest.of(0, 20));
        return new ApiResponse<>(flowLogPage.getContent());
    }

}
