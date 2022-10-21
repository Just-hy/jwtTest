package com.example.jwttest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jwttest.mapper.PermissionMapper;
import com.example.jwttest.mapper.PermissionRoleMapper;
import com.example.jwttest.pojo.Permission;
import com.example.jwttest.pojo.PermissionRole;
import com.example.jwttest.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/18
 * @Description:
 * @Version:  1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionRoleMapper permissionRoleMapper;
    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.selectList(null);
    }

    @Override
    public Boolean add(Permission permission) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pername",permission.getPername())
                .or()
                .eq("percode",permission.getPercode())
                .or()
                .eq("perurl",permission.getPerurl());
        if(permissionMapper.selectOne(queryWrapper)!=null)return false;
        int insert = permissionMapper.insert(permission);
        if(insert!=1) return false;
        return true;
    }

    @Override
    public Boolean perToRole(PermissionRole permissionRole) {
        QueryWrapper<PermissionRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("perid",permissionRole.getPerid())
                .eq("roleid",permissionRole.getRoleid());
        PermissionRole realpermissionRole = permissionRoleMapper.selectOne(queryWrapper);
        if(realpermissionRole!=null)return false;
        int insert = permissionRoleMapper.insert(permissionRole);
        return insert == 1;
    }


}