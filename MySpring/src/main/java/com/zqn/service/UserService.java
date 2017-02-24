package com.zqn.service;

import com.zqn.dao.UserDao;
import com.zqn.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2017/1/11.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;


    public void save(User user){
        userDao.save(user);

    }
    public void update(User user){
        userDao.update(user);

    }
}
