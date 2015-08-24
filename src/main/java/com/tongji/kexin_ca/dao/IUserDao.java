package com.tongji.kexin_ca.dao;

import com.tongji.kexin_ca.entity.User;


public interface IUserDao {

    User selectUserById(int id);

//    List<User> selectAllUsers();

    User selectUserByName(String name);

    int addUser(User user);

    int deleteUserById(int id);

    int updateUserByPrimaryKeySelective(User user);


}
