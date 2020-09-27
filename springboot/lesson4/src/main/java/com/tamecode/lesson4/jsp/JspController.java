package com.tamecode.lesson4.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: LiQiongchao
 * @Date: 2020/7/14 23:06
 */
@Controller
public class JspController {

    @RequestMapping("/jsp")
    public String jsp(Model model) {
        model.addAttribute("message", "lee");
        return "index";
    }

}
