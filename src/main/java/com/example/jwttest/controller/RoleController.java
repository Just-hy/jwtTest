package com.example.jwttest.controller;

import com.example.jwttest.pojo.Roleinfo;
import com.example.jwttest.pojo.Userinfo;
import com.example.jwttest.service.RoleinfoService;
import com.example.jwttest.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author:  Huangyi
 * @Date:  2022/10/19
 * @Description:
 * @Version:  1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleinfoService roleinfoService;
    @PostMapping("doadd")
    public JsonResult add(@RequestBody Roleinfo roleinfo){
        Boolean result = roleinfoService.add(roleinfo);
        if(result) {
            return JsonResult.ok().data("result",result);
        }else {
            return JsonResult.error().data("result",result);
        }
    }

    @PostMapping("doroletouser")
    public JsonResult roleToUser(Roleinfo roleinfo,
                                 Userinfo userinfo){
        Boolean result = roleinfoService.roleToUser(roleinfo,userinfo);
        if(result) {
            return JsonResult.ok().data("result",result);
        }else {
            return JsonResult.error().data("result",result);
        }
    }

}