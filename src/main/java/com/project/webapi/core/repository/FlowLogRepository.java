package com.project.webapi.core.repository;

import com.project.webapi.core.data.dao.FlowLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowLogRepository extends JpaRepository<FlowLog, Long> {
}
