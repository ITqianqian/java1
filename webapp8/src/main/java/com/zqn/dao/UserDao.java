package com.zqn.dao;

import com.zqn.entity.User;
import com.zqn.util.DbHelp;

/**
 * Created by dell on 2016/12/16.
 */
public class UserDao {


    public void save(User user) {
        String sql = "insert into user(username,password, email, phone, state, avatar)values(?,?,?,?,?,?)";
        DbHelp.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
