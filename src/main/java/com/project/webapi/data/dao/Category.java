package com.project.webapi.data.dao;

import com.project.webapi.core.data.ExaminableDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
public class Category extends ExaminableDao {

    public String name;
    public String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
