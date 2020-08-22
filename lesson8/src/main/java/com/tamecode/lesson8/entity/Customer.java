package com.tamecode.lesson8.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

/**
 * 客户实体类
 * @Author: LiQiongchao
 * @Date: 2020/8/22 10:06
 */
@Data
@Entity
// 通过属性注入，不通过方法注入
@Access(value = AccessType.FIELD)
@Table(name = "sb_jpa_customers")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    // 指定映射到数据库的字符长度
    @Column(length = 64)
    private String name;

    @OneToOne(orphanRemoval = true)
    private CreditCard creditCard;

    @ManyToOne
    private Store store;

    @ManyToMany
    private Collection<Book> books;

}
