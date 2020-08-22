package com.tamecode.lesson8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement(proxyTargetClass = true)
public class Lesson8Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson8Application.class, args);
    }

}
