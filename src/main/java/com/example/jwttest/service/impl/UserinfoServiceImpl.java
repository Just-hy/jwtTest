package com.example.jwttest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jwttest.mapper.*;
import com.example.jwttest.pojo.*;
import com.example.jwttest.service.PermissionService;
import com.example.jwttest.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/18
 * @Description:
 * @Version:  1.0
 */
@Service
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PermissionRoleMapper permissionRoleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleinfoMapper roleinfoMapper;


    @Override
    public Userinfo login(Userinfo userinfo) {
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userinfo.getUsername());
        queryWrapper.eq("password",userinfo.getPassword());
        return userinfoMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Permission> getPermissionByUserId(Integer userid) {
        List<Permission> permissions = new ArrayList<>();
        //1.根据用户id查询角色id
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);
        //2.循环用户具有的角色
        for (UserRole userRole : userRoles) {
            //3.根据角色id得到对应的权限id
            QueryWrapper<PermissionRole> permissionRoleQueryWrapper = new QueryWrapper<>();
            permissionRoleQueryWrapper.eq("roleid",userRole.getRoleid());
            List<PermissionRole> permissionRoles = permissionRoleMapper.selectList(permissionRoleQueryWrapper);
            //4.根据权限id得到权限的信息
            for (PermissionRole permissionRole : permissionRoles) {
                permissions.add(permissionMapper.selectById(permissionRole.getPerid()));
            }
        }
        return permissions;
    }

    @Override
    public List<Roleinfo> getRoleinfoByUserId(Integer userid) {
        List<Roleinfo> roleinfos = new ArrayList<>();
        //1.根据用户id查询角色id
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);
        for (UserRole userRole : userRoles) {
            roleinfos.add(roleinfoMapper.selectById(userRole.getRoleid()));
        }
        return roleinfos;
    }

    @Override
    public Boolean add(Userinfo userinfo) {
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userinfo.getUsername());
        Userinfo userexist = userinfoMapper.selectOne(queryWrapper);
        if(userexist!=null)return false;
        int insert = userinfoMapper.insert(userinfo);
        return insert == 1;
    }
}