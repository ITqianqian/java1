package com.zqn.dao;

import com.zqn.pojo.User;

import java.util.List;

/**
 * Created by dell on 2017/1/11.
 */
public interface UserDao {
    void save(User user);
    void update(User user);
    void delect(Integer id);
    User findById(Integer id);
    List<User> findAll();
    User findByName(String username);
    Long count();
}
