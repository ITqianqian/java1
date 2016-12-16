package com.zqn.service;

import com.sun.deploy.config.Config;
import com.zqn.dao.UserDao;
import com.zqn.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by dell on 2016/12/16.
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public void saveNewUser(String username, String password, String email, String phone) {
        User user = new User();
        user.setUsername(username);
        userDao.save(user);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
    }
}
