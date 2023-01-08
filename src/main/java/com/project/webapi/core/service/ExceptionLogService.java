package com.project.webapi.core.service;

import com.project.webapi.core.data.dao.ExceptionLog;
import com.project.webapi.core.repository.jpa.ExceptionLogRepository;
import org.springframework.stereotype.Service;

@Service
public class ExceptionLogService extends BaseService<ExceptionLog, ExceptionLogRepository> {
    public ExceptionLogService(ExceptionLogRepository repository) {
        super(repository);
    }
}
