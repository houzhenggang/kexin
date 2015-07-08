package com.lq.controller;

import com.lq.entity.User;
import com.lq.service.UserService;
import com.lq.util.TokenManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private TokenManager tokenManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "User/Login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap model, String user_name, String password, HttpServletResponse response) {

        User user = userService.userValidation(user_name, password);
        if (user == null) {
            model.addAttribute("errorMessage", "用户名或密码错误！");
            return "User/Login";
        }

        model.addAttribute("user", user);
        //cookie生成和写入
        String token = tokenManager.generateToken(user);
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "User/LoginSuccess";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "User/Register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, String re_password, ModelMap model) {
        if (!re_password.equals(user.getPassword())) {
            model.addAttribute("errorMessage", "密码不一致!");
            return "User/Register";
        }
        int result = userService.userRegister(user);
        if (result == -1) {
            model.addAttribute("errorMessage", "用户已注册!");
            return "User/Register";
        }
        return "User/RegisterSuccess";
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
}
