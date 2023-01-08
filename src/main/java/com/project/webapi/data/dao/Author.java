package com.project.webapi.data.dao;

import com.project.webapi.core.data.ExaminableDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "AUTHOR")
@Getter
@Setter
@NoArgsConstructor
public class Author extends ExaminableDao {

    public String name;

    @OneToMany(mappedBy="author", fetch = FetchType.EAGER)
    private Set<News> news;

    public Author(String name) {
        this.name = name;
    }
}
