package com.tamecode.lesson9.repository;

import com.tamecode.lesson9.bean.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用接口形式的仓储类【常用】
 *
 * @author Liqc
 * @date 2020/9/14 16:00
 */
@Repository
public interface PersonRepository2 extends PagingAndSortingRepository<Person, Long> {

    List<Person> findByName(String name);

}
