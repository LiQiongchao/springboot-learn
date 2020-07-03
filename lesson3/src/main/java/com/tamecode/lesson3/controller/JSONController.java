package com.tamecode.lesson3.controller;

import com.tamecode.lesson3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * JSON 格式测试
 *
 * @Author: LiQiongchao
 * @Date: 2020/7/2 23:20
 */
@RestController
public class JSONController {

    @Autowired
    ApplicationContext context;

    @Bean
    public User currentUser() {
        User user = new User();
        user.setName("LiSi");
        user.setAge(25);
        // 为 user 添加修改链接，调用一次会添加一次链接，因为使用的是同一个对象，放到 bean 的创建中就不会有这种现象
        user.add(linkTo(methodOn(JSONController.class).setUserName("Z3")).withSelfRel());
        user.add(linkTo(methodOn(JSONController.class).setUserAge(18)).withSelfRel());
        return user;
    }

    @Autowired
    @Qualifier("currentUser")
    private User user;

    /**
     * 输出结果：{"name":"LiSi","age":25,"_links":{"self":[{"href":"http://localhost:8080/json/user/set/name?name=Z3"},{"href":"http://localhost:8080/json/user/set/age?name=18"}]}}
     * @return
     */
    @GetMapping(value = "/json/user"
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public User user() {
        return user;
    }

    // setName
    @PostMapping(path = "/json/user/set/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public User setUserName(@RequestParam String name) {
        user.setName(name);
        return user;
    }

    // setAge
    @PostMapping(path = "/json/user/set/age", produces = MediaType.APPLICATION_JSON_VALUE)
    public User setUserAge(@RequestParam Integer age) {
        user.setAge(age);
        return user;
    }

}
