package com.example.jwttest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwttest.pojo.Userinfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserinfoMapper extends BaseMapper<Userinfo> {
    int deleteByPrimaryKey(Integer userid);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}