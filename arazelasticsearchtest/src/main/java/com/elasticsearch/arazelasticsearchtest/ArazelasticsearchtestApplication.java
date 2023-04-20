package com.elasticsearch.arazelasticsearchtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.elasticsearch.arazelasticsearchtest.repository")
public class ArazelasticsearchtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArazelasticsearchtestApplication.class, args);
	}

}
