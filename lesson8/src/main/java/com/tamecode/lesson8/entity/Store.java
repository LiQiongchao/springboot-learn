package com.tamecode.lesson8.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collection;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/22 15:32
 */
@Data
// 不加不会更新On update的时间戳
@DynamicUpdate
@Entity
@Table(name = "sb_jpa_stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128)
    private String name;

    @OneToMany(mappedBy = "store")
    private Collection<Customer> customers;

}
