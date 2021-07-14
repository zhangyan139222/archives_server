package com.nantian.security.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nantian.security.service.UserService;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.R;
import com.natian.doamin.Update;
import com.natian.entity.security.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "/user",tags = {"用户管理"})
public class UserController {

    @Autowired
    private UserService  userService;

    @PostMapping("/saveUser")
    @ApiOperation(value = "用户数据新增")
    public   R     saveSysUser( @RequestBody @ApiParam(value = "用户数据",required = true) @Validated({Insert.class}) User  user){
          return userService.save(user)? R.ok("用户数据新增成功"):R.error("用户数据新增失败");
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "用户数据更新")
    public   R  updateSysUser(  @RequestBody @ApiParam(value = "用户数据",required = true) @Validated({Update.class}) User  user){
        User byId = userService.getById(user.getUserId());
//        user.setVersion(byId.getVersion());
        return   userService.updateById(user)? R.ok("用户数据更新成功"):R.error("用户数据更新失败");
    }

    @PostMapping("/deleteUser/{id}")
    @ApiOperation(value = "用户数据删除")
    public R  deleteSysUser(@ApiParam(value = "用户ID",required = true) @PathVariable @Validated({Delete.class}) String id){
             return userService.removeById(id)? R.ok("用户数据删除成功"):R.error("用户数据删除失败");

    }

    @GetMapping("/getUserList/{page}/{size}")
    @ApiOperation(value = "用户数据列表")
    public R   getSysUserList(@ApiParam(value = "当前页码", required = true) @PathVariable Integer page,
                              @ApiParam( value = "每页记录数", required = true) @PathVariable Integer size,
                              @ApiParam(value = "用户数据",required = false) User  user){
        Page<User>  pages=new Page<>(page,size);
         try {
             IPage<User> sysUser1 = userService.getSysUser(pages, user);
             return  R.ok("获取用户数据列表成功").data("sysUser",sysUser1);
         }catch (Exception  e){
             e.printStackTrace();
             return  R.error("获取用户数据列表失败");
         }
    }



}
