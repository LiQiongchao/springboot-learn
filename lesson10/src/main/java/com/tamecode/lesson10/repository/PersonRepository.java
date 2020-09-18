package com.tamecode.lesson10.repository;

import com.tamecode.lesson10.entity.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 人员相关仓库
 *
 * @author Liqc
 * @date 2020/9/18 13:33
 */
@NoRepositoryBean
public interface PersonRepository {

    /**
     * 根据id查询人
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"persons"})
    Person findPersonFromCache(String id);

    /**
     * 保存
     * @param person
     * @return
     */
//    @CacheEvict(cacheNames = "persons")
    boolean savePersonFromCache(Person person);

    /**
     * 根据id查询人
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"persons"})
    Person findPersonFromRedis(String id);

    /**
     * 保存
     * @param person
     * @return
     */
//    @CacheEvict(cacheNames = "persons")
    boolean savePersonFromRedis(Person person);

}
