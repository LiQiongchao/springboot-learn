package com.tamecode.lesson4;

import com.tamecode.lesson4.servlet.MyServletRequestListener;
import com.tamecode.lesson4.spring.boot.MyFilter2;
import com.tamecode.lesson4.spring.boot.MyServlet2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import java.util.EventListener;

@ServletComponentScan(basePackages = {"com.tamecode.lesson4.servlet"})
@SpringBootApplication
public class Lesson4Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Lesson4Application.class, args);
        // 查看当前的工作目录
        System.out.println("working dir: " + System.getProperty("user.dir"));
    }

    // 注册 MyServlet2
    @Bean
    public static ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.setServlet(new MyServlet2());
        registrationBean.setName("myServlet2");
        registrationBean.addUrlMappings("/myservlet2", "/yourservlet2");
        registrationBean.addInitParameter("myname", "myValue");
        return registrationBean;
    }

    @Bean
    public static FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter2());
        registrationBean.setName("myFilter2");
        registrationBean.addUrlPatterns("/myservlet2");

        registrationBean.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.INCLUDE);
        return registrationBean;
    }

    @Bean
    public static ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<EventListener> registerBean = new ServletListenerRegistrationBean<>();
        // 可以多次注册，注册几次会执行几次
        registerBean.setListener(new MyServletRequestListener());
        return registerBean;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(Lesson4Application.class);
        return builder;
    }

}
