package com.tamecode.lesson4.spring.boot;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: LiQiongchao
 * @Date: 2020/7/14 22:40
 */
public class MyFilter2 extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        getServletContext().log("/myservlet2 was filtered!");
        filterChain.doFilter(request, response);
    }

}
