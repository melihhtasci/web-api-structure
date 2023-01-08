package com.project.webapi.data.dao;

import com.project.webapi.core.data.ExaminableDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "NEWS")
@Getter
@Setter
@NoArgsConstructor
public class News extends ExaminableDao implements Serializable {

    public static final long serialVersionUID = 1L;

    public News(String title, Category category, Author author) {
        this.title = title;
        this.category = category;
        this.author = author;
    }

    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public LocalDateTime publishedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="author_id", nullable=false)
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id", nullable=false)
    private Category category;
}
