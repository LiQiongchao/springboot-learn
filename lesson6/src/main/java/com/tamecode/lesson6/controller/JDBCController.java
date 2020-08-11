package com.tamecode.lesson6.controller;

import com.tamecode.lesson6.domain.Explore;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/11 22:08
 */
@RestController
@RequiredArgsConstructor
public class JDBCController {

    private final DataSource dataSource;

    @GetMapping(value = "/jdbc/explore", produces = {"application/json"})
    public Object getZhiHusByJdbc(@RequestParam(value = "id", defaultValue = "1") String id) {

        Explore explore = new Explore();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            // 方法一会有SQL注入的问题
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from zh_explore where id = " + id);

            // 方法二，不会有SQL注入的问题
            PreparedStatement statement = connection.prepareStatement("select * from zh_explore where id = ?");
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name;
                explore.setName(name = resultSet.getString("name"));
                System.out.println("查询到：" + name);
                explore.setId(resultSet.getLong("id"));
                explore.setUrl(resultSet.getString("url"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return explore;
    }

}
