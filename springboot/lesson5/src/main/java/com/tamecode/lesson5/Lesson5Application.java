package com.tamecode.lesson5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Lesson5Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Lesson5Application.class, args);
    }

    @RestController
    public static class MyController {

        @RequestMapping("/message")
        public String message() {
            return "Hello, World";
        }

    }

    @Bean
    public static WebServerFactoryCustomizer webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer() {

            @Override
            public void customize(WebServerFactory factory) {

                if (factory instanceof TomcatServletWebServerFactory) {
                    TomcatServletWebServerFactory serverFactory = (TomcatServletWebServerFactory) factory;

                    serverFactory.addContextCustomizers((context) -> {
                        context.setPath("/lesson5");
                    });

                    serverFactory.addConnectorCustomizers(connector -> {
                        connector.setPort(8888);
                    });
                }
            }

        };
    }

}
