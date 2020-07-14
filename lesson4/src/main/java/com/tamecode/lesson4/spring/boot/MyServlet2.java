package com.tamecode.lesson4.spring.boot;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet2 使用api注册的方式
 *
 * @Author: LiQiongchao
 * @Date: 2020/7/14 22:29
 */
public class MyServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        servletContext.log("myServlet2 do get .... ");
        PrintWriter writer = resp.getWriter();
        writer.write("<html><body>hello my servlet2 !! </body></html>");
    }
}
