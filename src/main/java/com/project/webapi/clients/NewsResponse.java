package com.project.webapi.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
public class NewsResponse {

    public String status;
    public int totalResults;
    public List<Article> articles;

}

