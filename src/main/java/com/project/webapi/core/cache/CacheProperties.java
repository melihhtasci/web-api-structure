package com.project.webapi.core.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("spring.redis")
public class CacheProperties {

    private String host;
    private int port;
    private int timeout;

}
