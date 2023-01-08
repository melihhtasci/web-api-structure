package com.project.webapi.data.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.webapi.core.data.ExaminableDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
public class Category extends ExaminableDao {

    public String name;
    public String description;

    @OneToMany(mappedBy="category", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<News> news;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
