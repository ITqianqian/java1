package com.zqn.service.impl;

import com.zqn.mapper.RolesMapper;
import com.zqn.mapper.UserMapper;
import com.zqn.pojo.Roles;
import com.zqn.pojo.User;
import com.zqn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by dell on 2017/1/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAll();
    }

    @Override
    public void save(User user) {
        userMapper.save(user);

    }

    @Override
    public void delUser(Integer id) {
        userMapper.del(id);

    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public List<Roles> findAllRoles() {
        return rolesMapper.findAll();
    }


}
