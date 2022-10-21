package com.example.jwttest.service;

import com.example.jwttest.pojo.Permission;
import com.example.jwttest.pojo.Roleinfo;
import com.example.jwttest.pojo.Userinfo;

import java.util.List;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/18
 * @Description:
 * @Version:  1.0
 */
public interface UserinfoService {
    Userinfo login(Userinfo userinfo);
    //得到用户的所有权限
    List<Permission> getPermissionByUserId(Integer userid);
    //得到用户的所有角色
    List<Roleinfo> getRoleinfoByUserId(Integer userid);

    Boolean add(Userinfo userinfo);
}
