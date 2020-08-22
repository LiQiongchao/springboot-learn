package com.tamecode.lesson8.repository;

import com.tamecode.lesson8.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Customer repository
 * 如果不使用接口的话，不能使用JDK动态代理，因为jdk动态代理是使用的是基于接口
 *
 * @Author: LiQiongchao
 * @Date: 2020/8/22 16:17
 */
@Repository
@Transactional(readOnly = false)
public class CustomerRepository extends SimpleJpaRepository {

    @Autowired
    public CustomerRepository(EntityManager entityManager) {
        super(Customer.class, entityManager);
    }

}
