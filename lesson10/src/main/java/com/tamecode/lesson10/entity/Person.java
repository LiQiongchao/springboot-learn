package com.tamecode.lesson10.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Liqc
 * @date 2020/9/18 13:35
 */
@Setter
@Getter
@ToString
public class Person implements Serializable {

    private String id;

    private String name;

    private int age;

}
