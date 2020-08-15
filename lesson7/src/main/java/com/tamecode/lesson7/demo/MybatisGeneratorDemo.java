package com.tamecode.lesson7.demo;

import com.tamecode.lesson7.entity.ZhExplore;
import com.tamecode.lesson7.entity.ZhExploreExample;
import com.tamecode.lesson7.mapper.ZhExploreMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/15 19:40
 */
public class MybatisGeneratorDemo {

    public static void main(String[] args) throws IOException {

        System.out.println(System.getProperty("basedir")); // null
        System.out.println(System.getProperty("user.dir")); // F:\WorkSpaces\practise-projects\springboot-learn

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = contextClassLoader.getResourceAsStream("mybatis/mybatis-config.xml");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStreamReader, "dev", new Properties());
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取Mapper
        ZhExploreMapper mapper = sqlSession.getMapper(ZhExploreMapper.class);

        ZhExploreExample example = new ZhExploreExample();
        ZhExploreExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(17);
        List<ZhExplore> zhExplores = mapper.selectByExample(example);
        System.out.println(zhExplores.get(0));
        sqlSession.close();
    }

}
