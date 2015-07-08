package com.lq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {


    @RequestMapping(value = "test")
    public String testIntercepter(HttpSession session) {
        System.out.println(session.getAttribute("user_id"));
        return "test";


    }
}
