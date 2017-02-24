package com.zqn.service;

import com.zqn.pojo.Roles;
import com.zqn.pojo.User;

import java.util.List;

/**
 * Created by dell on 2017/1/12.
 */
public interface UserService {
    List<User> findAllUser();

    void save(User user);

    void delUser(Integer id);

    User findUserById(Integer id);

    List<Roles> findAllRoles();
}
