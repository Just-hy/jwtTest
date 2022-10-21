package com.example.jwttest.controller;

import com.example.jwttest.pojo.Permission;
import com.example.jwttest.pojo.Userinfo;
import com.example.jwttest.service.UserinfoService;
import com.example.jwttest.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/17
 * @Description:
 * @Version:  1.0
 */
@Controller
public class LoginController {
    @Autowired
    private UserinfoService userinfoService;
    // 实现认证
    @PostMapping("/dologin")
    @ResponseBody
    public Map<String,Object> dologin(@RequestBody Userinfo user){
        Map<String,Object> jsonResult=new HashMap<>();
        // 模拟service验证登录信息
        Userinfo realuser = userinfoService.login(user);
        if(realuser!=null){
            Map<String , Object> body=new HashMap<>();
            body.put("username",user.getUsername());
            body.put("realname",user.getRealname());
            // 存储角色和权限
            //数据库操作
            List<Permission> permissions = userinfoService.getPermissionByUserId(realuser.getUserid());
            StringBuilder sb = new StringBuilder();
            if(permissions!=null){
                for (Permission permission : permissions) {
                    sb.append(",");
                    sb.append(permission.getPercode());
                }
                body.put("authorities",sb.substring(1));//截取第一个“，”
            }else {
                body.put("authorities","");
            }
            // 生成token 返回给客户端，然客户端自己保存
            String token= JwtUtils.createToken(body,20);
            jsonResult.put("data",token);
            jsonResult.put("errorCode",0);

        }else{
            jsonResult.put("errorCode",500);
        }
        return  jsonResult;
    }
    // 目的通过controller 转到   login.html ，实际访问 http://localhost:8080/tologin 会显示登录页面
    @GetMapping(path = "/tologin")
    public String tologin(){
        return "login";
    }
}