package com.example.jwttest.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * permission
 * @author 
 */
@Data
@TableName
public class Permission implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer perid;

    private String pername;

    private String percode;

    private String perurl;

    private static final long serialVersionUID = 1L;
}