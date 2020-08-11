package com.tamecode.lesson6.controller;

import com.tamecode.lesson6.domain.Explore;
import com.tamecode.lesson6.service.ExploreService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/11 22:08
 */
@RestController
@RequiredArgsConstructor
public class JDBCController {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private final ExploreService exploreService;

    /**
     * 使用原生的JDBC dataSource查询
     * Statement 的execute() 与 executeUpdate()的区别（通过查看注释说明得知）
     *      execute: 一个是用于返回bool类型的操作，如DDL等
     *      executeUpdate: 一般用于用于 insert, update 返回的是影响的行业。
     *
     * @param id
     * @return
     */
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

    /**
     * 使用Spring提供的 {@link JdbcTemplate}（封装的连接池，封装了常用方法）保存
     * @param explore
     * @return
     */
    @PostMapping("/explore")
    public Map<String, Object> addExplore(@RequestBody Explore explore) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", exploreService.save(explore));
        map.put("success", exploreService.save2(explore));
        return map;
    }

}
