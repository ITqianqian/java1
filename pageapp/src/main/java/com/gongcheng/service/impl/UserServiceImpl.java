package com.gongcheng.service.impl;

import com.gongcheng.exception.NotFoundException;
import com.gongcheng.mapper.RoleMapper;
import com.gongcheng.mapper.UserMapper;
import com.gongcheng.pojo.Role;
import com.gongcheng.pojo.User;
import com.gongcheng.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * Created by dell on 2017/2/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Value("${password.salt}")
    private String salt;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void save(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
        userMapper.save(user);
    }

    @Override
    public User findUserById(Integer id) {
        User user=userMapper.findById(id);
        if(user==null){
            throw new NotFoundException();
        }else{
            return user;
        }

    }

    @Override
    @Transactional
    public void saveNewUser(User user, Integer[] roleIds) {
        //1.保存用户
        user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
        userMapper.save(user);
        User user1=userMapper.findByName(user.getUserName());
        //2.建立用户与角色之间的关系
        if(roleIds != null){
            for(Integer roleId:roleIds){
                Role role= roleMapper.findById(roleId);
                if(role != null){
                    roleMapper.saveNewUserRole(user1.getId(),roleId);
                }

            }

        }

    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAll();
    }

    @Override
    public List<Role> findRoleByUserId(Integer id) {
        return roleMapper.findRoleByUserId(id);
    }

    @Override
    public void delUserById(Integer id) {
        User user = userMapper.findById(id);
        if(user==null){
            throw new NotFoundException();
        }else{
            //删除角色
            roleMapper.delRoleByUserId(id);
            //删除用户
            userMapper.delUser(id);
        }
    }

    @Override
    @Transactional
    public void editUser(User user, Integer[] roleIds) {
        //删除原有角色
        roleMapper.delRoleByUserId(user.getId());
        //保存新角色
        for(Integer id:roleIds){
            roleMapper.saveNewUserRole(user.getId(),id);
        }
        //更新用户
        if(StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));

        }
    }


}
