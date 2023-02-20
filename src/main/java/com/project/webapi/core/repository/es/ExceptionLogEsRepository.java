package com.project.webapi.core.repository.es;

import com.project.webapi.core.data.dao.ExceptionLog;
import com.project.webapi.core.data.dao.FlowLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ExceptionLogEsRepository extends ElasticsearchRepository<ExceptionLog, Long> {

    Page<ExceptionLog> findByType(String type, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"type\": \"?0\"}}]}}")
    Page<FlowLog> findByTypeUsingCustomQuery(String type, Pageable pageable);

}
