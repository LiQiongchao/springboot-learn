package com.tamecode.lesson7.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamecode.lesson7.entity.ExploreDigest;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/16 12:00
 */
public class DigestTypeHandler extends BaseTypeHandler {

    /**
     * json字符串与对象的转换工具
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        try {
            StringWriter stringWriter = new StringWriter();
            objectMapper.writeValue(stringWriter, parameter);
            ps.setString(i, stringWriter.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        ExploreDigest digest = null;
        try {
            if (StringUtils.hasText(value)) {
                digest = objectMapper.readValue(value, ExploreDigest.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return digest;
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        ExploreDigest digest = null;
        try {
            if (StringUtils.hasText(value)) {
                digest = objectMapper.readValue(value, ExploreDigest.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return digest;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
