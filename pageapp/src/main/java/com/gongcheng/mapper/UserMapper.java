package com.gongcheng.mapper;

import com.gongcheng.pojo.User;

import java.util.List;

/**
 * Created by dell on 2017/2/16.
 */
public interface UserMapper {
    List<User> findAll();
    void save(User user);
    User findById(Integer id);

    void delUser(Integer id);


    User findByName(String userName);
}
