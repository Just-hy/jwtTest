package com.example.jwttest.controller;

import com.example.jwttest.pojo.Permission;
import com.example.jwttest.pojo.PermissionRole;
import com.example.jwttest.pojo.Roleinfo;
import com.example.jwttest.service.PermissionService;
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
@RequestMapping("/per")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @PostMapping("doadd")
    public JsonResult add(@RequestBody Permission permission){
        Boolean result = permissionService.add(permission);
        if(result) {
            return JsonResult.ok().data("result",result);
        }else {
            return JsonResult.error().data("result",result);
        }
    }

    @PostMapping("dopertorole")
    public JsonResult perToRole(@RequestBody PermissionRole permissionRole){
        Boolean result = permissionService.perToRole(permissionRole);
        if(result) {
            return JsonResult.ok().data("result",result);
        }else {
            return JsonResult.error().data("result",result);
        }
    }

}