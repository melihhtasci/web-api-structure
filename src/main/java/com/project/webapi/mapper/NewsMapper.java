package com.project.webapi.mapper;

import com.project.webapi.core.mapper.BaseMapper;
import com.project.webapi.data.dao.News;
import com.project.webapi.data.dto.NewsDto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public final class NewsMapper extends BaseMapper<News, NewsDto> {

    @Override
    public News toEntity(NewsDto dto) {
        return null;
    }

    @Override
    public List<News> toEntity(List<NewsDto> users) {
        return null;
    }

    @Override
    public NewsDto toDto(News entity) {
        return NewsDto.builder()
                .author(entity.getAuthor().name)
                .category(entity.getCategory().name)
                .description(entity.getDescription())
                .publishedAt(entity.getCreatedDate())
                .title(entity.getTitle())
                .url(entity.getUrl())
                .urlToImage(entity.getUrlToImage()).build();
    }

    @Override
    public List<NewsDto> toDto(List<News> users) {
        List<NewsDto> dtoList = new ArrayList<>();
        users.stream().map(s -> toDto(s)).forEach(s -> dtoList.add(s));
        return dtoList;
    }
}
