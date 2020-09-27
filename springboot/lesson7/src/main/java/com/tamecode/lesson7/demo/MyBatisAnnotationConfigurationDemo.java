package com.tamecode.lesson7.demo;

import com.tamecode.lesson7.entity.ZhExploreDemo;
import com.tamecode.lesson7.mapper.ZhExploreAnnotationMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 使用注解模式查询
 * @Author: LiQiongchao
 * @Date: 2020/8/16 11:42
 */
public class MyBatisAnnotationConfigurationDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = contextClassLoader.getResourceAsStream("mybatis/mybatis-config.xml");

        // 把输入流转换为读取模式
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "dev");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ZhExploreAnnotationMapper zhExploreAnnotationMapper = sqlSession.getMapper(ZhExploreAnnotationMapper.class);
        ZhExploreDemo zhExploreDemo = zhExploreAnnotationMapper.selectExploreById(17);

        System.out.println(zhExploreDemo);
        // ZhExploreDemo(id=17, exploreName=NBA2, url=www.nba.com, digest=ExploreDigest(id=12, desc=no desc))

        sqlSession.close();
    }


}
