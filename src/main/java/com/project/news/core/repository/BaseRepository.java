package com.project.news.core.repository;

import com.project.news.core.data.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository extends JpaRepository<User, Long> {
}
