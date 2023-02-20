package com.project.webapi.core.service.es;

import com.project.webapi.core.data.dao.ExceptionLog;
import com.project.webapi.core.repository.es.ExceptionLogEsRepository;
import com.project.webapi.core.repository.jpa.ExceptionLogRepository;
import com.project.webapi.core.service.BaseElasticService;
import org.springframework.stereotype.Service;

@Service
public class ExceptionLogElasticService extends BaseElasticService<ExceptionLog, ExceptionLogEsRepository> {
    public ExceptionLogElasticService(ExceptionLogEsRepository repository) {
        super(repository);
    }
}
