package com.tamecode.lesson3.controller;

import com.tamecode.lesson3.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LiQiongchao
 * @Date: 2020/7/2 22:41
 */
@RestController
@RequestMapping("/xml")
public class XMLController {

    /**
     * 当支持多解析方式时，比如 Json, Xml 时，要看请求头里的 Accept 能解析哪些字段，哪个在前，就先用哪个
     * text/html,application/xhtml+xml,application/xml;q=0.9,image/webp
     *  解析成 XML 格式
     * text/html,application/json,application/xhtml+xml,application/xml;q=0.9,image/webp
     *  解析成 Json 格式的
     * @return
     */
    @GetMapping("/user")
    public User user() {
        User user = new User();
        user.setName("lee");
        user.setAge(20);
        return user;
    }

    /**
     * 除了根据 请求头的 Accept 决定返回什么格式，也可以通过设置服务端 produces 支持返回哪些格式
     * consumes 设置此接口能消费请求头中 Content-Type(内容格式) 支持的格式，如果不支持就会返回 (type=Unsupported Media Type, status=415)
     * @return
     */
    @GetMapping(value = "/user2"
//            , consumes = {MediaType.APPLICATION_XML_VALUE}
    , produces = {MediaType.APPLICATION_XML_VALUE})
    public User user2() {
        User user = new User();
        user.setName("chao");
        user.setAge(22);
        return user;
    }

}
