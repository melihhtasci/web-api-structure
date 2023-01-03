package com.project.webapi.core.mapper;

import com.project.webapi.core.data.ExaminableDao;
import com.project.webapi.core.data.dao.User;

import java.util.List;

public abstract class BaseMapper<T extends ExaminableDao, D> {

    User toEntity(D dto) {
       return null;
    };
    List<T> toEntity(List<D> users) {
        return null;
    }
    D toDto(T entity) {
        return null;
    }
    List<D> toDto(List<T> users) {
        return null;
    }

}
