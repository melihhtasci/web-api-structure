package com.project.webapi.data.dao;

import com.project.webapi.core.data.ExaminableDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
public class Category extends ExaminableDao {

    public String name;
    public String description;

    @OneToMany(mappedBy="category")
    private Set<News> news;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}