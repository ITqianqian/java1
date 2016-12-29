package com.zqn.service;

import com.zqn.dao.AdminDao;
import com.zqn.dao.NodeDao;
import com.zqn.dao.ReplyDao;
import com.zqn.dao.TopicDao;
import com.zqn.entitiy.Admin;
import com.zqn.exception.ServiceException;
import com.zqn.util.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dell on 2016/12/29.
 */
public class AdminService {
    Logger logger = LoggerFactory.getLogger(AdminService.class);
    AdminDao adminDao = new AdminDao();
    TopicDao topicDao = new TopicDao();
    ReplyDao replyDao = new ReplyDao();
    NodeDao nodeDao = new NodeDao();


    public Admin login(String adminname, String password, String ip) {
        Admin admin = adminDao.findbyname(adminname);
        if(admin != null && admin.getPassword().equals(DigestUtils.md5Hex(Config.get("user.password.salt") + password))){
            logger.debug("管理员{}登录了后台管理系统,IP为:{}",adminname,ip);
            return admin;
        }else{
            throw new ServiceException("帐号密码错误");
        }



    }

    public void delTopicById(String id) {
        adminDao.delTopicbyid(id);
    }
}

