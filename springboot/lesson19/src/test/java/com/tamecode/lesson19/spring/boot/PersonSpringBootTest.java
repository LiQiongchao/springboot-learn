package com.tamecode.lesson19.spring.boot;

import com.tamecode.lesson19.configuration.PersonConfiguration;
import com.tamecode.lesson19.domain.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试PersonConfiguration
 *
 * @Author: LiQiongchao
 * @Date: 2020/9/26 15:08
 */
@RunWith(SpringRunner.class)
// 只加载PersonConfiguration, 不会加载其它的 content
@SpringBootTest(classes = PersonConfiguration.class)
public class PersonSpringBootTest {

    @Autowired
    private Person person;

    @Test
    public void primaryPersonTest() {
        Assert.assertEquals(Long.valueOf(1), person.getId());
        Assert.assertEquals("gaga", person.getName());
        Assert.assertEquals(Integer.valueOf(23), person.getAge());
    }



}
