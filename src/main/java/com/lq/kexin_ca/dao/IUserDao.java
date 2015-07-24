package com.lq.kexin_ca.dao;

import com.lq.kexin_ca.entity.User;


public interface IUserDao {

    User selectUserById(int id);

//    List<User> selectAllUsers();

    User selectUserByName(String name);

    int addUser(User user);

    int deleteUserById(int id);

    int updateUserByPrimaryKeySelective(User user);

}
