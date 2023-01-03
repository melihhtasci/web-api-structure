package com.project.webapi.repository;

import com.project.webapi.data.dao.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByName(String name);

}
