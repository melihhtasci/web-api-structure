package com.project.webapi.core.data.dao;

import com.project.webapi.core.data.ExaminableDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
public class User extends ExaminableDao implements Serializable {

    public String name;
    public String email;
    public String password;
    public String passwordSalt;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordSalt = password +"***"; // todo write password salt method
    }

}
