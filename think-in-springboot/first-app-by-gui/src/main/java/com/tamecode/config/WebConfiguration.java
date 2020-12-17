package com.tamecode.config;

import com.tamecode.firstrappbygui.FirstrAppByGuiApplication;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
 * {@link SpringBootApplication} 和 {@link EnableAutoConfiguration} 同样能激活自动装配，但是对于标注Bean的类型是有差异的。
 * @author Liqc
 * @date 2020/12/16 13:57
 */
//@Configuration
@SpringBootApplication
//@EnableAutoConfiguration
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
     * {@link ApplicationRunner#run(ApplicationArguments)} 方法在SpringBoot启动后调用一次。
     * 查看用的是 web 容器，webflux 的 netty 容器
     * @param beanFactory
     * @return
     */
    @Bean
    public ApplicationRunner runner(BeanFactory beanFactory) {
        return args -> {
            System.out.println("当前 helloWorld Bean 实现类为: "
                    + beanFactory.getBean("helloWorld").getClass().getName());
            System.out.println("当前 WebConfiguration Bean 实现类为: "
                    + beanFactory.getBean(WebConfiguration.class).getClass().getName());
        };
        /*
        * 使用 @EnableAutoConfiguration 结果:
        * 当前 helloWorld Bean 实现类为: org.springframework.web.reactive.function.server.RouterFunctions$DefaultRouterFunction
        * 当前 WebConfiguration Bean 实现类为: com.tamecode.config.WebConfiguration
        *
        * 使用 @SpringBootApplication 结果:
        * 当前 helloWorld Bean 实现类为: org.springframework.web.reactive.function.server.RouterFunctions$DefaultRouterFunction
        * 当前 WebConfiguration Bean 实现类为: com.tamecode.config.WebConfiguration$$EnhancerBySpringCGLIB$$a1088d6f
        *
        * */
    }

    /**
     * ServletWebServerInitializedEvent 和 ReactiveWebServerInitializedEvent
     * 是 WebServerInitializedEvent 的实现类
     * 监控父类可以覆盖更广的场景，可以监控非 Web，
     * 比上面的方法{@link WebConfiguration#runner(WebServerApplicationContext)}更健壮。
     * @param event
     */
    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("当前 WebServer 实现类为: " + event.getWebServer().getClass().getName());
    }

}
