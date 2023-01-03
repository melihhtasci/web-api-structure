package com.project.webapi.clients;

import lombok.Getter;

import java.util.List;

@Getter
public class NewsResponse {

    public String status;
    public int totalResults;
    public List<Article> articles;

}

