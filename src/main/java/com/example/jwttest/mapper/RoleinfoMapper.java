package com.example.jwttest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwttest.pojo.Roleinfo;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleinfoMapper extends BaseMapper<Roleinfo> {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Roleinfo record);

    int insertSelective(Roleinfo record);

    Roleinfo selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Roleinfo record);

    int updateByPrimaryKey(Roleinfo record);
}