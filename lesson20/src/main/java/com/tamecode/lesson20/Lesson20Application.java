package com.tamecode.lesson20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson20Application {

    public static void main(String[] args) {
        // 方式一
//        SpringApplication.run(Lesson20Application.class, args);

        // 方式二
        SpringApplication springApplication = new SpringApplication(Lesson20Application.class);
        // 设置启动模式
//        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

}
