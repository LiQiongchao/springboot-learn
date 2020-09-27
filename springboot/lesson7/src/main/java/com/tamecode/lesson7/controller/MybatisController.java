package com.tamecode.lesson7.controller;

import com.tamecode.lesson7.entity.ZhExploreDemo;
import com.tamecode.lesson7.mapper.ZhExploreAnnotationMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/16 12:48
 */
@RestController
public class MybatisController {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Autowired(required = false)
    ZhExploreAnnotationMapper annotationMapper;

    @RequestMapping("/explore/{id}")
    public Object explore(@PathVariable Integer id) {
        ZhExploreDemo explore = sqlSessionTemplate.selectOne("com.tamecode.lesson7.mapper.ZhExploreDemo.selectExploreDemo", id);
        return explore;
    }

    @RequestMapping("/annotation/explore/{id}")
    public Object annotationExplore(@PathVariable Integer id) {
        ZhExploreDemo explore = annotationMapper.selectExploreById(id);
        return explore;
    }


}
