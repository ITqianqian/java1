package com.zqn.mapper;

import com.zqn.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/1/4.
 */
public interface UserMapper {
    User findById(Integer id);
    List<User> findAll();
    List<User> findByIds(List<Integer> idList);
    void save (User user);
    void Save (List<User> userList);
    User findByUsernameAndPassword(Map<String,Object> param);
    User findByParam(Map<String,Object> param);

}
