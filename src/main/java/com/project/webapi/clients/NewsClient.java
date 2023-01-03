package com.project.webapi.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "news", url = "https://newsapi.org/v2/top-headlines")
public interface NewsClient {


    @GetMapping("?country=tr&category={category}&apiKey={apiKey}")
    NewsResponse getByCategory(@PathVariable String category, @PathVariable String apiKey);

    @GetMapping("?country={country}&apiKey={apiKey}")
    NewsResponse get(@PathVariable String country, @PathVariable String apiKey);
}
