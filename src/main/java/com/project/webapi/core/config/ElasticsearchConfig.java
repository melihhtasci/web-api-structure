package com.project.webapi.core.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

@Configuration
class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private String port;

    @Bean
    RestHighLevelClient elasticSearchClient() {
        final ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo(host.concat(":").concat(port)).build();
        return RestClients.create(configuration).rest();
    }

}