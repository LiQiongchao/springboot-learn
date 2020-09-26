package com.tamecode.lesson19.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Person 测试
 *
 * @Author: LiQiongchao
 * @Date: 2020/9/26 10:28
 */
public class PersonTest {

    @Test
    public void personTest() {
        Person person = new Person();
        person.setAge(11);
        person.setName("hu");
        person.setId(2L);
        Assert.assertNotNull(person);
    }

}