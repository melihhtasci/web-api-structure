package com.project.webapi.repository;

import com.project.webapi.data.dao.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
