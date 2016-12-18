package com.zqn.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.zqn.dao.UserDao;
import com.zqn.entity.User;
import com.zqn.util.Config;
import com.zqn.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by dell on 2016/12/16.
 */
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao = new UserDao();


    //发送激活邮件的TOKEN缓存
    private static Cache<String,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(6, TimeUnit.HOURS)
            .build();

    public void saveNewUser(String username, String password, String email, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(DigestUtils.md5Hex(Config.get("user.password.salt")));
        user.setStatus(user.USERSTATE_UNACTIVE);
        user.setAvater(user.DEFAULT_AVATAR_NAME);
        userDao.save(user);

        //创建一个子线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给用户发激活邮件
                String uuid = UUID.randomUUID().toString();
                String url = "http://http://localhost:8080/user/active?_="+uuid;
                //放入缓存6小时
                cache.put(uuid,username);

                String html ="<h3>Dear "+username+":</h3>请点击<a href=" +
                        "'"+url+"'>" + "该链接</a>激活你的账号. <br>";

                EmailUtil.sendHtmlEmail(email,"用户激活邮件",html);


            }
        });
        thread.start();


    }
}
