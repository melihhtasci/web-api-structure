package com.project.webapi.data.dto;

import com.project.webapi.core.data.BaseDto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class NewsDto extends BaseDto {
    public String title;
    public String category;
    public String author;
    public String description;
    public String url;
    public String urlToImage;
    public LocalDateTime publishedAt;

}
