package com.zqn.dao;

import com.zqn.entity.Login;
import com.zqn.util.DbHelp;

/**
 * Created by dell on 2016/12/19.
 */
public class LoginDao {
    public void save(Login login) {
        String sql = "insert into login(ip,user_id)values(?,?)";
        DbHelp.update(sql,login.getIp(),login.getUser_id());
    }
}
