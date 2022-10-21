package com.example.jwttest.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * userinfo
 * @author 
 */
@Data
@TableName
@AllArgsConstructor
@NoArgsConstructor
public class Userinfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userid;

    private String username;

    private String password;

    private String realname;

    private static final long serialVersionUID = 1L;
}