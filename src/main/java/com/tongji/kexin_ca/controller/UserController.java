package com.tongji.kexin_ca.controller;

import com.tongji.kexin_ca.entity.User;
import com.tongji.kexin_ca.service.UserService;
import com.tongji.kexin_ca.util.TokenManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    public static final String adminName = "lq";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "User/Login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html")
    public String loginHTML(ModelMap model, String user_name, String password, HttpServletResponse response) {

        if (handleLogin(model, user_name, password, response)) {

            return "redirect:/monitor";
        }
        return "User/Login";

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "!text/html")
    @ResponseBody
    public String loginOther(ModelMap model, String user_name, String password, HttpServletResponse response) {

        if (!handleLogin(model, user_name, password, response)) return "LoginFail";
        int userId = ((User) model.get("user")).getUserId();
        return "LoginSuccess:" + userId;
    }


    private boolean handleLogin(ModelMap model, String user_name, String password, HttpServletResponse response) {
        User user = userService.userValidation(user_name, password);
        if (user == null) {
            model.addAttribute("errorMessage", "用户名或密码错误！");
            return false;
        }

        model.addAttribute("user", user);

        //cookie生成和写入
        String token = TokenManager.generateToken(user);
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "User/Register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/html")
    public String registerHTML(User user, ModelMap model) {

        int result = userService.userRegister(user);
        if (result == -1) {
            model.addAttribute("errorMessage", "用户已注册!");
            return "User/Register";
        }
        return "User/RegisterSuccess";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "!text/html")
    @ResponseBody
    public String registerOther(User user) {

        int result = userService.userRegister(user);
        if (result == -1) {
            return "RegisterFail:UserNameHasExisted";
        }
        return "RegisterSuccess";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}