package com.project.webapi.core.repository.jpa;

import com.project.webapi.core.data.dao.ExceptionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionLogRepository extends JpaRepository<ExceptionLog, Long> {
}
