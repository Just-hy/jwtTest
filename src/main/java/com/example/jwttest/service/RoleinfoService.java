package com.example.jwttest.service;

import com.example.jwttest.pojo.Roleinfo;
import com.example.jwttest.pojo.Userinfo;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/19
 * @Description:
 * @Version:  1.0
 */
public interface RoleinfoService {
    Boolean add(Roleinfo roleinfo);
    //赋予用户角色
    Boolean roleToUser(Roleinfo roleinfo, Userinfo userinfo);
}
