package com.tamecode.lesson8.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 信用卡
 * @Author: LiQiongchao
 * @Date: 2020/8/22 15:11
 */
@Data
@Entity
@Table(name = "sb_jpa_credit_cards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128)
    private String number;

    @Column(name = "reg_date")
    private LocalDateTime registeredDate;

    /**
     * mappedBy在别一个关联对象中的名称，并且不作为拥用方owe，
     */
    @OneToOne(mappedBy = "creditCard")
    private Customer customer;

}
