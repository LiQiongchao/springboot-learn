package com.tamecode.lesson9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class Lesson9Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson9Application.class, args);
    }

}
