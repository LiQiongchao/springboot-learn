package com.tamecode.lesson8.service;

import com.tamecode.lesson8.entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 客户服务
 *
 * @Author: LiQiongchao
 * @Date: 2020/8/22 16:04
 */
@Service
public class CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 添加客户
     * @param customer
     */
    @Transactional
    public void addCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }


}
