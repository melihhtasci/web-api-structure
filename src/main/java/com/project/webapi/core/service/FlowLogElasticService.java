package com.project.webapi.core.service;

import com.project.webapi.core.data.dao.FlowLog;
import com.project.webapi.core.repository.es.FlowLogEsRepository;
import org.springframework.stereotype.Service;

@Service
public class FlowLogElasticService extends BaseElasticService<FlowLog, FlowLogEsRepository> {
    public FlowLogElasticService(FlowLogEsRepository repository) {
        super(repository);
    }
}
