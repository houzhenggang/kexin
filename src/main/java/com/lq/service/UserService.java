package com.lq.service;

import com.lq.dao.IUserDao;
import com.lq.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {


    @Resource
    private IUserDao userDao;

    public int userRegister(User user) {

        User u = userDao.selectUserByName(user.getName());
        if (u != null) {
            return -1; //用户名已存在
        }
        userDao.addUser(user);
        return 1;
    }

    public User userValidation(String username, String password) {
        User u = userDao.selectUserByName(username);
        if (u == null) {
            return null; //用户名不存在
        }
        if (!u.getPassword().equals(password)) {
            return null; //密码错误
        }
        return u;
    }

    public User getUserByName(String username) {
        return userDao.selectUserByName(username);
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
