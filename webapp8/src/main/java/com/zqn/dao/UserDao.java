package com.zqn.dao;

import com.zqn.entity.User;
import com.zqn.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by dell on 2016/12/16.
 */
public class UserDao {

    /**
     * 添加新用户
     * @param user
     */
    public void save(User user) {
        String sql = "insert into user(username,password, email, phone, status, avatar)values(?,?,?,?,?,?)";
        DbHelp.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone(),user.getStatus(),user.getAvater());
    }

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    public User findByName(String username) {
        String sql = "select * from user where username=?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),username);
    }

    /**
     * 根据邮件查找
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        String sql = "select * from user where email = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),email);
    }
}
