package com.tamecode.lesson20.properties;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;

/**
 * @author Liqc
 * @date 2020/9/24 10:37
 */
@Setter
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigAutoConfigurationTest {

    @Autowired
    com.tamecode.lesson20.autoconfiguration.ConfigProperties configProperties;

    @Test
    public void contextLoads() {
        // ConfigProperties(list=[list0, list1, list2], map1={name=lee, age=18}, map2={name=lee2, age=19})
        System.out.println(configProperties.toString());
    }


}
