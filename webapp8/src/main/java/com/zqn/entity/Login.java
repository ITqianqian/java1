package com.zqn.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by dell on 2016/12/19.
 */
public class Login implements Serializable {
    private Integer id;
    private Timestamp logintime;
    private String ip;
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getLogintime() {
        return logintime;
    }

    public void setLogintime(Timestamp logintime) {
        this.logintime = logintime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
