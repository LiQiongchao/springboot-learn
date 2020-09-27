package com.tamecode.lesson19;

import org.junit.Test;
import org.springframework.mock.web.MockServletContext;

/**
 * @Author: LiQiongchao
 * @Date: 2020/9/26 15:06
 */
public class MockServletApiTest {

    @Test
    public void mockServletContextTest() {
        // 不支持 Servlet 3.0 的注册 API
        MockServletContext servletContext = new MockServletContext();
        servletContext.setInitParameter("abc", "def");
    }


}
