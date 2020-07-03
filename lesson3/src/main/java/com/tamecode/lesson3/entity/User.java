package com.tamecode.lesson3.entity;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * @Author: LiQiongchao
 * @Date: 2020/7/2 22:44
 */
@Data
public class User extends RepresentationModel<User> {

    private String name;

    private Integer age;

}
