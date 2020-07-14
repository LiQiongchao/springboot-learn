package com.tamecode.lesson4.servlet;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 监控 /myservlet 的请求，会在doGet() 或 doPost()之前执行
 *
 * @Author: LiQiongchao
 * @Date: 2020/7/14 21:45
 */
@WebFilter(urlPatterns = {"/myservlet"})
public class MyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        getServletContext().log("/myservlet was filtered!");
        filterChain.doFilter(request, response);
    }
}
