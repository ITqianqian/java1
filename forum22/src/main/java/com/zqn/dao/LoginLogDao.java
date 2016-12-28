package com.zqn.dao;

import com.zqn.entitiy.LoginLog;
import com.zqn.util.DbHelp;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public class LoginLogDao {
    public void save(LoginLog log) {
        String sql="insert into t_loginlog(ip,userid)values(?,?)";
        DbHelp.update(sql,log.getIp(),log.getUserid());
    }
}
