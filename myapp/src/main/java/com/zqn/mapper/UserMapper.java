package com.zqn.mapper;

import com.zqn.pojo.User;

import java.util.List;

/**
 * Created by dell on 2017/1/12.
 */
public interface UserMapper {

        void save(User user);
        void update(User user);
        List<User> findAll();
        void del(Integer id);
        User findById(Integer id);



}
