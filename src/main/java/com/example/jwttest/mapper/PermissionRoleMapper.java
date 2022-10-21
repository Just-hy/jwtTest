package com.example.jwttest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwttest.pojo.PermissionRole;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRoleMapper extends BaseMapper<PermissionRole> {
    int deleteByPrimaryKey(Integer perroleid);

    int insert(PermissionRole record);

    int insertSelective(PermissionRole record);

    PermissionRole selectByPrimaryKey(Integer perroleid);

    int updateByPrimaryKeySelective(PermissionRole record);

    int updateByPrimaryKey(PermissionRole record);
}