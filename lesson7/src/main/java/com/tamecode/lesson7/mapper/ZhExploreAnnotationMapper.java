package com.tamecode.lesson7.mapper;

import com.tamecode.lesson7.entity.ZhExploreDemo;
import com.tamecode.lesson7.handler.DigestTypeHandler;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * 使用注解模式
 * 如果在SpringBoot中使用，就不用在mybatis-config.xml中配置映射了，会自动扫描。否则需要在配置文件中配置。
 *
 * @ResultMap("exploreMap") 中指定的id是XML已经存在的ResultMap的id
 *
 *
 * @Author: LiQiongchao
 * @Date: 2020/8/16 11:29
 */
@Mapper
public interface ZhExploreAnnotationMapper {


    /**
     * 使用注释方式使用MyBatis查询
     * @param id
     * @return
     */
    @Results(id = "exploreMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "exploreName", column = "name"),
            @Result(property = "digest", column = "digest", typeHandler = DigestTypeHandler.class)
    })
    @Select("SELECT `name`, id, url, digest FROM `zh_explore` WHERE id = #{id}")
    ZhExploreDemo selectExploreById(int id);

}
