package com.example.jwttest.service;

import com.example.jwttest.pojo.Permission;
import com.example.jwttest.pojo.PermissionRole;

import java.util.List;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/18
 * @Description:
 * @Version:  1.0
 */
public interface PermissionService {
    List<Permission> getAllPermissions();
    Boolean add(Permission permission);
    //赋予角色权限
    Boolean perToRole(PermissionRole permissionRole);
}
