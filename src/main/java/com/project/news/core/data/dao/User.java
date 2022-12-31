package com.project.news.core.data.dao;

import com.project.news.core.data.ExaminableDao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class User extends ExaminableDao implements Serializable {

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordSalt = password +"***";
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    private String name;
    private String email;
    private String password;
    private String passwordSalt;

}
