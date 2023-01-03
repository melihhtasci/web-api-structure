package com.project.webapi.repository;

import com.project.webapi.data.dao.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
