package com.gongcheng.service;

import com.gongcheng.pojo.Role;
import com.gongcheng.pojo.User;

import java.util.List;

/**
 * Created by dell on 2017/2/16.
 */
public interface UserService {
    List<User> findAll();

    void save(User user);

    User findUserById(Integer id);

    void saveNewUser(User user, Integer[] roleIds);


    List<Role> findAllRole();


    List<Role> findRoleByUserId(Integer id);

    void delUserById(Integer id);

    void editUser(User user, Integer[] roleIds);
}
