package com.lq.kexin.controller;

import com.lq.kexin.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {


    @RequestMapping(value = "test")
    @ResponseBody
    public User testIntercepter() {
        User u = new User();
        u.setPassword("12");
        u.setName("qw");
        u.setEmail("fefe@123.com");
        return u;
    }

}
