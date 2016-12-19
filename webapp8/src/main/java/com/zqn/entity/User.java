package com.zqn.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by dell on 2016/12/15.
 */
public class User implements Serializable {
    //默认头像
    public static final String DEFAULT_AVATAR_NAME = "default-avatar.jpg";
    //用户状态0未激活，1正常  2已被禁用
    public static final Integer USERSTATE_UNACTIVE = 0;

    public static final Integer USERSTATE_ACTIVE = 1;

    public static final Integer USERSTATE_DISABLED = 2;

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer status;
    private Timestamp creattime;
    private String avater;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreattime() {
        return creattime;
    }

    public void setCreattime(Timestamp creattime) {
        this.creattime = creattime;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }
}
