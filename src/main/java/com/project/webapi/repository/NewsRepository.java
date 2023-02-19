package com.project.webapi.repository;

import com.project.webapi.data.dao.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
