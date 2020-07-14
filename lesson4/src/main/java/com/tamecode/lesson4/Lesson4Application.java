package com.tamecode.lesson4;

import com.tamecode.lesson4.spring.boot.MyFilter2;
import com.tamecode.lesson4.spring.boot.MyServlet2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

@ServletComponentScan(basePackages = {"com.tamecode.lesson4.servlet"})
@SpringBootApplication
public class Lesson4Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson4Application.class, args);
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

}
