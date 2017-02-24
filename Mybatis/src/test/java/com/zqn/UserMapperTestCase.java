package com.zqn;

import com.zqn.mapper.UserMapper;
import com.zqn.pojo.User;
import com.zqn.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/1/6.
 */
public class UserMapperTestCase {
    private SqlSession sqlSession ;
    private UserMapper userMapper ;
    @Before
    public void setup(){
        sqlSession = SqlSessionFactoryUtil.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);


    }
    @After
    public void close(){
        sqlSession.close();
    }

    @Test
    public void findByParam(){
        Map<String,Object> param = new HashMap<String,Object>();
        //param.put("username","jack");
       param.put("password","123");
       

        User user = userMapper.findByParam(param);
        System.out.println(user);
    }




}
