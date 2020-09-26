package com.tamecode.lesson19;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

/**
 * 环境测试
 *
 * @Author: LiQiongchao
 * @Date: 2020/9/26 11:07
 */
public class EnvironmentTest {

    @Test
    public void systemPropertyTest() {
        Assert.assertNotNull(System.getProperty("os.arch"));
        MockEnvironment environment = new MockEnvironment();
        //        Environment environment = new StandardEnvironment();
//        Environment webEnvironment = new StandardServletEnvironment();
        environment.setProperty("user.country", "EN");
        Assert.assertEquals("EN", environment.getProperty("user.country"));
    }

    @Test
    public void managementSecurityEnableTest() {
        MockEnvironment environment = new MockEnvironment();
        environment.setProperty("management.security.enabled", "true");
        Assert.assertEquals(true, environment.getProperty("management.security.enabled", boolean.class));
    }

    @Test
    public void managementSecurityDisableTest() {
        MockEnvironment environment = new MockEnvironment();
        environment.setProperty("management.security.enabled", "false");
        Assert.assertEquals(false, environment.getProperty("management.security.enabled", boolean.class));
    }

}
