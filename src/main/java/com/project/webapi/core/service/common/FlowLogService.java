package com.project.webapi.core.service.common;

import com.project.webapi.core.data.dao.FlowLog;
import com.project.webapi.core.repository.jpa.FlowLogRepository;
import com.project.webapi.core.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class FlowLogService extends BaseService<FlowLog, FlowLogRepository> {
    public FlowLogService(FlowLogRepository repository) {
        super(repository);
    }
}
