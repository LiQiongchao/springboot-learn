package com.tamecode.lesson7.demo;

import com.tamecode.lesson7.entity.ZhExploreDemo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/15 19:40
 */
public class MybatisXmlConfigurationDemo {

    public static void main(String[] args) throws IOException {

        System.out.println(System.getProperty("basedir")); // null
        System.out.println(System.getProperty("user.dir")); // F:\WorkSpaces\practise-projects\springboot-learn

        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource resource = resourceLoader.getResource("classpath:/mybatis/mybatis-config.xml");

        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

//        InputStream inputStream = resource.getInputStream();

        Reader reader = encodedResource.getReader();

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(reader, "dev", new Properties());
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ZhExploreDemo explore = sqlSession.selectOne("com.tamecode.lesson7.mapper.ZhExploreDemo.selectExploreDemo", 17);
        System.out.println(explore);
        sqlSession.close();
    }

}
