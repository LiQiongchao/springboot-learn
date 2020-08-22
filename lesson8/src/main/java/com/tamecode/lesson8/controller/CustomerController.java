package com.tamecode.lesson8.controller;

import com.tamecode.lesson8.entity.Customer;
import com.tamecode.lesson8.repository.CustomerRepository;
import com.tamecode.lesson8.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/22 16:07
 */
@RestController
@RequestMapping(value = "customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 使用 EntityManager 操作
     * @param customer
     * @return
     */
    @PostMapping("add")
    public Customer add(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        Long id = customer.getId();
        return customerService.getCustomerById(id);
    }

    @RequestMapping("all")
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
