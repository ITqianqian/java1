package com.zqn.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.zqn.dao.LoginDao;
import com.zqn.dao.UserDao;
import com.zqn.entity.Login;
import com.zqn.entity.User;
import com.zqn.exception.ServiceException;
import com.zqn.util.Config;
import com.zqn.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by dell on 2016/12/16.
 */
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao = new UserDao();
    private LoginDao loginDao = new LoginDao();


    //发送激活邮件的TOKEN缓存
    private static Cache<String,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(6, TimeUnit.HOURS)
            .build();

    public void saveNewUser(String username, String password, String email, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(DigestUtils.md5Hex(Config.get("user.password.salt")+password));
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

    /**
     * 看用户名是否被占用
     * @param username 新用户名
     */
    public Boolean validatevUserName(String username) {
        //  去配置文件读 是否含有保留名字

        String name = Config.get("no.signup.usernames");
        //按照，分割
        List<String> nameList = Arrays.asList(name.split(","));
        if(nameList.contains(username)){
            return false;
        }else {
            //用户未被占用 返回null
            return userDao.findByName(username)==null;

        }


    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param ip
     */
    public User login(String username, String password, String ip) {
        User user = userDao.findByName(username);
        if(user != null && password.equals(user.getPassword())) {
        //if(user != null && DigestUtils.md5Hex(Config.get("user.password.salt") + password).equals(user.getPassword())) {
            if(user.getStatus().equals(User.USERSTATE_ACTIVE)) {
                //记录登录日志
                Login log = new Login();
                log.setIp(ip);
                log.setUser_id(user.getId());

                loginDao.save(log);

                logger.info("{}登录了系统，IP：{}",username,ip);
                return user;

            } else if(User.USERSTATE_UNACTIVE.equals(user.getStatus())) {
                throw new ServiceException("该账号未激活");
            } else {
                throw new ServiceException("该账号已被禁用");
            }
        } else {
            throw new ServiceException("账号或密码错误");
        }
    }
}
