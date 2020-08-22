package com.tamecode.lesson8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// 使用JPARepository
@EnableJpaRepositories
// 因为repository没有使用接口，所以要使用CGlib代理，无法使用JDK动态代理
@EnableTransactionManagement(proxyTargetClass = true)
public class Lesson8Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson8Application.class, args);
    }

}
