package com.nantian.security.controller;

import com.nantian.security.service.PermissionService;
import com.natian.doamin.R;
import com.natian.entity.security.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/17 11:23
 * @Description:
 */
@RestController
@RequestMapping("/permission")
@Api(value="/permission",tags ={"数据权限"})
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @ApiOperation(value="获取用户数据权限")
    @GetMapping("/getpermission/{userId}")
    public R PermissionListByUser(@ApiParam(value="用户名",required = true)   @PathVariable String userId){

        try{
            List<Permission> permissions = permissionService.PermissionListByUser(userId);
           return R.ok("获取用户数据权限成功").data("permissions",permissions);
        }catch (Exception  e){
            return R.ok("获取用户数据权限失败");
        }


    }
}
