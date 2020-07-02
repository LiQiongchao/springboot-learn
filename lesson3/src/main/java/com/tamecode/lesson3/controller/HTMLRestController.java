package com.tamecode.lesson3.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Author: LiQiongchao
 * @Date: 2020/7/1 21:55
 */

// @RestController = {@Controller, @ResponseBody}
@RestController
//@Controller
public class HTMLRestController {

    // value == path, 能匹配两个 URI 和 GET 和 POST 方法。
//    @RequestMapping(path = {"/html/demo", "/html/demo2"}, method = {RequestMethod.GET, RequestMethod.POST})

    // 如果要想根据 HttpMethod 匹配两个 URI, 只能是第一个Mapping有效，哪个在上面，哪个有效
    @PostMapping(value = {"html/demo2"})
    @GetMapping(path = {"/html/demo"})
//    @RequestMapping(value = "/html/demo", method = RequestMethod.GET)
//    @ResponseBody
    public String html() {
        return "<html><body><h1>Hello, World</h1></body></html>";
    }

    // @PathVariable
    @GetMapping(path = "/html/demo/{msg}")
    public String htmlPathVariable(@PathVariable String msg) {
        return "<html><body><h1>Hello, "+msg+"</h1></body></html>";
    }

    // @RequestParam
    @GetMapping(path = "/html/demo/params")
    public String htmlParams(@RequestParam(value = "p", required = false, defaultValue = "empty") String name
            , @RequestParam(required = false, defaultValue = "0") Integer age
            , HttpServletRequest request) {
        String favour = request.getParameter("favour");
        return "<html><body><h1>Hello, "+name+"</h1> you age is "+ age + " ,favour is "+ favour +" </body></html>";
    }

    // @RequestHeader
    @GetMapping(path = "/html/demo/header")
    public String htmlHeader(@RequestHeader(value = "Accept") String acceptType
            , HttpServletRequest request) {
//        String accept = request.getHeader("Accept");
        return "<html><body><h1>Accept = "+acceptType+"</h1></body></html>";
    }

    // @CookieValue
    @GetMapping(path = "/html/demo/cookie")
    public String htmlCookie(@CookieValue(required = false, defaultValue = "empty") String app_key
            , HttpServletRequest request) {
        return "<html><body><h1>cookie app_key = "+app_key+"</h1></body></html>";
    }

    // ResponseEntity 可以定制响应头信息
    @GetMapping(path = "/html/demo/resp")
    public ResponseEntity htmlEntity(HttpServletRequest request) {
        String html = "<html><body><h1>response entity</h1></body></html>";
        HttpHeaders headers = new HttpHeaders();
        headers.put("MyHeader", Arrays.asList("myValue"));
        ResponseEntity<String> responseEntity = new ResponseEntity<>(html, headers, HttpStatus.OK);
        return responseEntity;
    }


}
