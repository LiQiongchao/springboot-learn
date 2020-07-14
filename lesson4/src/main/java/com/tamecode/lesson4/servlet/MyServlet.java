package com.tamecode.lesson4.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 手写 servlet
 *
 * @Author: LiQiongchao
 * @Date: 2020/7/14 20:59
 */
@WebServlet(name = "myServlet",
        urlPatterns = {"/myservlet", "/yourservlet"},
        initParams = {
                @WebInitParam(name = "myname", value = "myServletValue")
        }
)
public class MyServlet extends HttpServlet {

    private String value;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 此处要把 ServletConfig 赋值给成员变量，否则后面其它地方不能直接使用成员变量
        super.init(config);
        config.getServletContext().log("myServlet init ....");
        this.value = config.getInitParameter("myname");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletContext servletContext = req.getServletContext();
        ServletContext servletContext = getServletContext();
        servletContext.log("myServlet do get .... ");
        PrintWriter writer = resp.getWriter();
        writer.write("<html><body>hello " + value + " my servlet !! </body></html>");
        // <html><body>hello myServletValue my servlet !! </body></html>
    }

}
