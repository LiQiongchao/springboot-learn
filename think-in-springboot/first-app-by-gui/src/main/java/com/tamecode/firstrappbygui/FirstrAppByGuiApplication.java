package com.tamecode.firstrappbygui;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class FirstrAppByGuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstrAppByGuiApplication.class, args);
    }

    /**
     * webflux 实现接口
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> helloWorld() {
        return route(GET("/hello-world"), request -> ok().body(Mono.just("Hello, World"), String.class));
    }

    /**
     *
     * @param context
     * @return
     */
    @Bean
    public ApplicationRunner runner(WebServerApplicationContext context) {
        return args -> {
            System.out.println("当前 WebServer 实现类为: " + context.getWebServer().getClass().getName());
        };
    }

}
