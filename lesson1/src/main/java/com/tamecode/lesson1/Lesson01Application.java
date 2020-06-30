package com.tamecode.lesson1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
// 扫描 servlet 的服务
@ServletComponentScan(basePackages = "com.tamecode.lesson1.web.servlet")
public class Lesson01Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson01Application.class, args);
    }

}
