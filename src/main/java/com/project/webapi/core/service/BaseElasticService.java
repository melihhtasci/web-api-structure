package com.project.webapi.core.service;

import com.project.webapi.core.data.ExaminableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public abstract class BaseElasticService<T extends ExaminableDao, R extends ElasticsearchRepository<T, Long>> {
    public R repository;

    @Autowired
    public BaseElasticService(R repository) {
        this.repository = repository;
    }

    public T save (T document) {
        document = repository.save(document);
        return document;
    }

}
