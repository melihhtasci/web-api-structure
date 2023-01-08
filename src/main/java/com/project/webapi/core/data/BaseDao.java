package com.project.webapi.core.data;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseDao implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
}
