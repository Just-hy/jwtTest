package com.example.jwttest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jwttest.mapper.RoleinfoMapper;
import com.example.jwttest.mapper.UserRoleMapper;
import com.example.jwttest.mapper.UserinfoMapper;
import com.example.jwttest.pojo.Roleinfo;
import com.example.jwttest.pojo.UserRole;
import com.example.jwttest.pojo.Userinfo;
import com.example.jwttest.service.RoleinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/19
 * @Description:
 * @Version:  1.0
 */
@Service
public class RoleinfoServiceImpl implements RoleinfoService {
    @Autowired
    private RoleinfoMapper roleinfoMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Override
    public Boolean add(Roleinfo roleinfo) {
        QueryWrapper<Roleinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rolename",roleinfo.getRolename())
                .or()
                .eq("rolecode",roleinfo.getRolecode());
        if(roleinfoMapper.selectOne(queryWrapper)!=null) return false;
        int insert = roleinfoMapper.insert(roleinfo);
        return insert == 1;
    }



    @Override
    public Boolean roleToUser(Roleinfo roleinfo, Userinfo userinfo) {
        Integer userid = userinfo.getUserid();
        Integer roleid = roleinfo.getRoleid();
        QueryWrapper<Userinfo> userWrapper = new QueryWrapper<>();
        Userinfo realuser = userinfoMapper.selectById(userid);
        if(realuser==null) return false;
        QueryWrapper<Roleinfo> roleWrapper = new QueryWrapper<>();
        Roleinfo realrole = roleinfoMapper.selectById(roleid);
        if(realrole==null) return false;
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid)
                    .eq("roleid",roleid);
        UserRole userRole = userRoleMapper.selectOne(queryWrapper);
        if(userRole!=null)return false;
        UserRole insertUserRole = new UserRole();
        insertUserRole.setRoleid(roleid);
        insertUserRole.setUserid(userid);
        int insert = userRoleMapper.insert(insertUserRole);
        return insert == 1;
    }
}