package com.tamecode.firstrappbygui;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * {@link SpringBootApplication#scanBasePackages()} 默认扫描类所在包下子包的所有配置类。
 */
@SpringBootApplication(scanBasePackages = "com.tamecode.config")
/*@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan*/
public class FirstrAppByGuiApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(FirstrAppByGuiApplication.class);
//        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
//        SpringApplication.run(FirstrAppByGuiApplication.class, args);
    }


    /**
     * {@link ApplicationRunner#run(ApplicationArguments)} 方法在SpringBoot启动后调用一次。
     * 查看用的是 web 容器，webflux 的 netty 容器
     * @param context
     * @return
     */
//    @Bean
    public ApplicationRunner runner(WebServerApplicationContext context) {
        return args -> {
            System.out.println("当前 WebServer 实现类为: " + context.getWebServer().getClass().getName());
        };
    }

}
