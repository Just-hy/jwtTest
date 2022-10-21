package com.example.jwttest.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * permission_role
 * @author 
 */
@Data
@TableName
public class PermissionRole implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer perroleid;

    private Integer roleid;

    private Integer perid;

    private static final long serialVersionUID = 1L;
}