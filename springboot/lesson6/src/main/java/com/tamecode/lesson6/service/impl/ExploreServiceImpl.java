package com.tamecode.lesson6.service.impl;

import com.tamecode.lesson6.domain.Explore;
import com.tamecode.lesson6.service.ExploreService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/11 22:54
 */
@Service
@RequiredArgsConstructor
// 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableTransactionManagement
public class ExploreServiceImpl implements ExploreService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public Boolean save(Explore explore) {
        return jdbcTemplate.execute("insert into zh_explore(name, url) values(?, ?)", new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, explore.getName());
                ps.setString(2, explore.getUrl());
                return ps.executeUpdate();
            }
        }).intValue() > 0;
    }


    @Override
    public Boolean save2(Explore explore) {

        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();

        return null;
    }
}
