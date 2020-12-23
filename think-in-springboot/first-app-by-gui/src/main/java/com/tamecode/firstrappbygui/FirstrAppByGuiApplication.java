package com.tamecode.firstrappbygui;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;

/**
 * {@link SpringBootApplication#scanBasePackages()} 默认扫描类所在包下子包的所有配置类。
 */
//@SpringBootApplication(scanBasePackages = "com.tamecode.config")
/*@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan*/
@EnableAutoConfiguration
public class FirstrAppByGuiApplication {

    /**
     * {@link SpringApplication#SpringApplication(Class[])} 的参数是 {@link SpringBootApplication} annotation 标注的类。
     * @param args
     */
    public static void main(String[] args) {
//        SpringApplication application = new SpringApplication(WebConfiguration.class);
//        application.setWebApplicationType(WebApplicationType.NONE);
//        application.run(args);
        SpringApplication.run(FirstrAppByGuiApplication.class, args);
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
