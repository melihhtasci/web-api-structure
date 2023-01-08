package com.project.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(basePackages = {"com.project.webapi.repository", "com.project.webapi.core.repository.jpa"})
@EnableElasticsearchRepositories(basePackages = {"com.project.webapi.core.repository.es"})
public class WebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApiApplication.class, args);
	}

}
