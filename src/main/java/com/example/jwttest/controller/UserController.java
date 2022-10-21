package com.example.jwttest.controller;

import com.example.jwttest.pojo.Permission;
import com.example.jwttest.pojo.Roleinfo;
import com.example.jwttest.pojo.Userinfo;
import com.example.jwttest.service.UserinfoService;
import com.example.jwttest.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/18
 * @Description:
 * @Version:  1.0
 */
@RestController
@RequestMapping(path = "/user")
//@PreAuthorize("hasRole('user')")
public class UserController {
    @Autowired
    private UserinfoService userinfoService;

    @PostMapping("doadd")
    public JsonResult add(@RequestBody Userinfo userinfo){
        Boolean result = userinfoService.add(userinfo);
        if(result) {
            return JsonResult.ok().data("result",result);
        }else {
            return JsonResult.error().data("result",result);
        }
    }
    //@PreAuthorize("hasAuthority('user_edit')")
    @GetMapping("dogetroles/{id}")
    public JsonResult getroles(@PathVariable Integer id){
        List<Roleinfo> roleinfoByUserId = userinfoService.getRoleinfoByUserId(id);
        return JsonResult.ok().data("result",roleinfoByUserId);
    }
    @GetMapping("dogetpers/{id}")
    public JsonResult getpers(@PathVariable Integer id){
        List<Permission> permissionByUserId = userinfoService.getPermissionByUserId(id);
        return JsonResult.ok().data("result",permissionByUserId);
    }
    @GetMapping("show")
    public JsonResult showName(){
//认证对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return JsonResult.ok().data("username",authentication.getPrincipal()).data("authorities",authentication.getAuthorities());
//未认证过，但是经过Security框架，那么会有一个临时用户 anonymousUser
//        return JsonResult.error().data("username",principal.toString());
    }
}