package com.example.jwttest.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * user_role
 * @author 
 */
@Data
@TableName
public class UserRole implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userroleid;

    private Integer userid;

    private Integer roleid;

    private static final long serialVersionUID = 1L;
}