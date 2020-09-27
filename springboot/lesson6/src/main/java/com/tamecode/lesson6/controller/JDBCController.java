package com.tamecode.lesson6.controller;

import com.tamecode.lesson6.domain.Explore;
import com.tamecode.lesson6.service.ExploreService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * 查询当前的连接是否支持事务
     * @return
     */
    @GetMapping("/jdbc/meta/transaction/supported")
    public boolean supportedTransaction() {
        boolean supported = false;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            DatabaseMetaData databaseMetaData = connection.getMetaData();

            supported = databaseMetaData.supportsTransactions();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supported;
    }




    /**
     * 在不知道字段的情况下查询所有的字段及映射
     * @return
     */
    @GetMapping(value = "/explores", produces = {"application/json;charset=utf-8"})
    public List<Map<String, Object>> getExplores() {

        return jdbcTemplate.execute(new StatementCallback<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> doInStatement(Statement statement) throws SQLException, DataAccessException {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM zh_explore");
                // 元数据
                ResultSetMetaData metaData = resultSet.getMetaData();
                // 列的数量
                int columnCount = metaData.getColumnCount();
                List<String> columnList = new ArrayList<>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    // the first column is 1, the second is 2
                    String columnName = metaData.getColumnName(i);
                    columnList.add(columnName);
                }

                List<Map<String, Object>> list = new ArrayList<>(columnCount);
                while (resultSet.next()) {

                    Map<String, Object> map = new HashMap<>();

                    for (String column : columnList) {
                        map.put(column, resultSet.getObject(column));
                    }

                    list.add(map);
                }

                return list;
            }
        });
    }


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
     * 手动加事务
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/jdbc/explore/tr", produces = {"application/json"})
    public Object getZhiHusByJdbcWithTr(@RequestParam(value = "id", defaultValue = "1") String id) {

        Explore explore = new Explore();
        Connection connection = null;
        Savepoint savepoint = null;
        try {
            connection = dataSource.getConnection();
            // 手动提交事务，模拟JDBC自动处理事务
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint();

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

            // 提交事务
            connection.commit();

        } catch (SQLException e) {
            if (connection == null) {
                try {
                    // 还原事务
                    connection.rollback(savepoint);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    // 需要再改成自动提交事务
                    connection.setAutoCommit(true);
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
//        map.put("success", exploreService.save2(explore));
        return map;
    }

}
