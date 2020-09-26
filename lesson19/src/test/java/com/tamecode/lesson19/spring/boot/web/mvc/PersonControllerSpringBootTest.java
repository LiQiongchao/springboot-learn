package com.tamecode.lesson19.spring.boot.web.mvc;

import com.tamecode.lesson19.Controller.PersonController;
import com.tamecode.lesson19.configuration.PersonConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * {@link PersonController} 测试
 *
 * @Author: LiQiongchao
 * @Date: 2020/9/26 15:17
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@Import(PersonConfiguration.class)
public class PersonControllerSpringBootTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void indexTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
