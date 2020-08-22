package com.tamecode.lesson8.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/22 15:55
 */

@Data
// 会customer表中添加discount的字段
@Inheritance
@Entity
public class VipCustomer extends Customer {

    private int discount;

}
