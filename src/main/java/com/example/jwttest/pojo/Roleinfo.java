package com.example.jwttest.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * roleinfo
 * @author 
 */
@Data
@TableName
public class Roleinfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer roleid;

    private String rolename;

    private String rolecode;

    private static final long serialVersionUID = 1L;
}