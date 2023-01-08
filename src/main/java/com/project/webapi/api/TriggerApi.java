package com.project.webapi.api;

import com.project.webapi.core.data.ApiResponse;
import com.project.webapi.core.data.dao.FlowLog;
import com.project.webapi.core.service.FlowLogElasticService;
import com.project.webapi.data.dao.Category;
import com.project.webapi.service.CategoryService;
import com.project.webapi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

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

    @GetMapping("/{category}")
    private ApiResponse<String> triggerClient(@PathVariable String category) throws Exception {
        String result = newsService.getNews(category);
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

    @GetMapping("/match-query/{type}")
    private ApiResponse<List<SearchHit<FlowLog>>> matchQueryExample(@PathVariable String type) {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("type", type))
                // NativeSearchQuery >>>
                // > matchQuery or ile calisir.
                // belirli bir yuzde uzerinde eslesen arama sonuclarini filtrelemek icin minimumShouldMatch(100%);
                // 100 verirsek and gibi calisir.
                // > full text search
                // and olarak calismasini istiyorsak matchquery().operator(Operator.AND)
                // > fuzzines yazim hatasi icin kullanilir.
                // .fuzziness(Fuzziness.ONE) // ONE 1 karakter anlamina gelir.
                // .prefixLength(2)) // ilk iki karakterin degismeyecegi anlamina gelir

                .build();
        return new ApiResponse<>(elasticsearchOperations.search(searchQuery, FlowLog.class,
                IndexCoordinates.of("flow_log")).getSearchHits());
    }

}
