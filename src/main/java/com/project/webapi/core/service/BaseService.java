package com.project.webapi.core.service;

import com.project.webapi.core.data.ExaminableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//@Repository
public abstract class BaseService<T extends ExaminableDao, R extends JpaRepository<T, Long>> {

    public R repository;

    @Autowired
    public BaseService(R repository) {
        this.repository = repository;
    }

    public T add(T entity) {
        setInitialValuesForAdd(entity);
        repository.save(entity);
        return entity;
    }

    public List<T> addAll(List<T> list) {
        list.forEach(e -> setInitialValuesForAdd(e));
        return repository.saveAllAndFlush(list);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T update(T entity) {
        setInitialValuesForUpdate(entity);
        return repository.save(entity);
    }

   public Optional<T> getById(long id) {
        return repository.findById(id);
    }

    private T setInitialValuesForAdd(T entity) {
        entity.setActive(true);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setCreatedBy(1);
        entity.setUpdatedBy(1);
        return entity;
    }

    private T setInitialValuesForUpdate(T entity) {
        entity.setUpdatedDate(LocalDateTime.now());
        // todo set updatedBy entity.setUpdatedBy(1);
        return entity;
    }

}
