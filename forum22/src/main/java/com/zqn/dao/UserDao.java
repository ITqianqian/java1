package com.zqn.dao;


import com.zqn.entitiy.User;
import com.zqn.util.DbHelp;
import com.zqn.util.Page;
import com.zqn.vo.UserVo;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

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

    public Integer count() {
        //获取所有状态不为0用户的数量
        String sql = "select count(*) from t_user where state != 0 order by id";
        return DbHelp.query(sql,new ScalarHandler<Long>()).intValue();
    }

    public List<User> findAllUsers(Page<UserVo> page) {
        //分页查询状态不为0的所有用户
        String sql = "select * from t_user where state != 0 order by createtime limit ?,?";
        return DbHelp.query(sql,new BeanListHandler<User>(User.class),page.getStart(),page.getPageSize());
    }

    public UserVo findUserVo(Integer id) {
        String sql = "select tll.logintime lastLoginTime,tll.ip loginIP,tu.id userId,tu.username username,tu.createtime ,tu.state userState from t_loginlog tll ,t_user tu where userid = ? order by logintime desc limit 0,1";
        return DbHelp.query(sql,new BeanHandler<UserVo>(UserVo.class),id);
    }

    public List<User> findAllUser() {
        String sql="select * from t_user";
        return DbHelp.query(sql,new BeanListHandler<User>(User.class));

    }
}
