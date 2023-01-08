package com.project.webapi.data.dao;

import com.project.webapi.core.data.ExaminableDao;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LANGUAGE")
@Getter
@Setter
public class Language extends ExaminableDao {
    private String name;

    public Language(String name) {
        this.name = name;
    }
}
