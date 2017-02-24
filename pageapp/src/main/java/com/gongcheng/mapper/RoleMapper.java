package com.gongcheng.mapper;

import com.gongcheng.pojo.Role;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dell on 2017/2/17.
 */
public interface RoleMapper {
    List<Role> findAll();

    Role findById(Integer roleId);


    void saveNewUserRole(@Param("userId") Integer id,
                         @Param("roleId") Integer roleId);

    List<Role> findRoleByUserId(@Param("userId") Integer id);

    void delRoleByUserId(@Param("userId") Integer id);


}
