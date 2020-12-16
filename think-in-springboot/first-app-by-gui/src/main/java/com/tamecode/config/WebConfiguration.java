package com.tamecode.config;

import com.tamecode.firstrappbygui.FirstrAppByGuiApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author Liqc
 * @date 2020/12/16 13:57
 */
@Configuration
public class WebConfiguration {

    /**
     * webflux 实现接口
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> helloWorld() {
        return route(GET("/hello-world"), request -> ok().body(Mono.just("Hello, World"), String.class));
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
