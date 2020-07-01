package com.tamecode.lesson3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


}
