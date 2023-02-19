package com.project.webapi.core.mapper;

import com.project.webapi.core.data.BaseDto;
import com.project.webapi.core.data.ExaminableDao;
import com.project.webapi.core.data.dao.User;
import com.project.webapi.data.dao.News;
import com.project.webapi.data.dto.NewsDto;

import java.util.List;

public abstract class BaseMapper<T extends ExaminableDao, D extends BaseDto> {

    public abstract T toEntity(D dto);
    public abstract List<T> toEntity(List<D> dtos);
    public abstract D toDto(T entity);
    public abstract List<D> toDto(List<T> entities);

}
