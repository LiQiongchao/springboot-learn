package com.tamecode.firstrappbygui;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class FirstrAppByGuiApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(FirstrAppByGuiApplication.class);
//        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
//        SpringApplication.run(FirstrAppByGuiApplication.class, args);
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

    /**
     * ServletWebServerInitializedEvent 和 ReactiveWebServerInitializedEvent
     * 是 WebServerInitializedEvent 的实现类
     * 监控父类可以覆盖更广的场景，可以监控非 Web，
     * 比上面的方法{@link FirstrAppByGuiApplication#runner(WebServerApplicationContext)}更健壮。
     * @param event
     */
    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("当前 WebServer 实现类为: " + event.getWebServer().getClass().getName());
    }

}
