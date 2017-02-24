package com.zqn.dao.impl;

import com.zqn.dao.UserDao;
import com.zqn.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by dell on 2017/1/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoImplTest {
    @Autowired
    private UserDao userDao;




    @Test
    public void save() throws Exception {
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("123456789");
        userDao.save(user);



    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delect() throws Exception {

    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findByName() throws Exception {

    }

    @Test
    public void count() throws Exception {

    }

}