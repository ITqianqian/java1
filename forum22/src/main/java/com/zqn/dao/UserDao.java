package com.zqn.dao;


import com.zqn.entitiy.User;
import com.zqn.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDao {
    public void save(User user) {
        String sql="insert into t_user(username,password,email,phone,state,avatar)values(?,?,?,?,?,?)";
        DbHelp.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone(),user.getState(),user.getAvatar());
    }

    public User findByUsername(String username) {
        String sql="select * from t_user where username  = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),username);
    }

    public User findByEmail(String email) {
        String sql="select * from t_user where email = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),email);
    }

    public void update(User user) {
        String sql="update t_user set password=?,email=?,phone=?,state=?,avatar=? where id=?";
        DbHelp.update(sql,user.getPassword(),user.getEmail(),user.getPhone(),user.getState(),user.getAvatar(),user.getId());

    }

    public User findById(Integer id) {
        String sql="select * from t_user where id=?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),id);
    }
}
