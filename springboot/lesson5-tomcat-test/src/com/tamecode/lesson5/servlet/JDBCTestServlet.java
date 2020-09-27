package com.tamecode.lesson5.servlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/5 22:23
 */
public class JDBCTestServlet extends HttpServlet {

    private DataSource dataSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/TestDB");

            // 使用env
            String bean = (String) envContext.lookup("Bean");
            System.out.println(bean);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES;");
            while (resultSet.next()) {
                String databases = resultSet.getString(1);
                writer.write(databases + "\n");
                writer.flush();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
