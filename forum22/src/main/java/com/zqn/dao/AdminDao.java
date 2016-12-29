package com.zqn.dao;

import com.zqn.entitiy.Admin;
import com.zqn.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by dell on 2016/12/29.
 */
public class AdminDao {
    public Admin findbyname(String adminname) {
        String sql = "select * from t_admin where adminname=?";
        return DbHelp.query(sql,new BeanHandler<>(Admin.class),adminname);
    }


}
