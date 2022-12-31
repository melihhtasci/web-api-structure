package com.project.webapi.core.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
}
