package com.nantian.security.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nantian.security.service.RoleService;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.R;
import com.natian.doamin.Update;
import com.natian.entity.security.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

//@RestController
//@Api(value = "/role",tags = {"角色管理"})
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("/saveRole")
    @ApiOperation(value = "角色数据新增")
    public R saveRole(@RequestBody @ApiParam(value = "角色数据",required = true) @Validated({Insert.class}) Role role){
        return roleService.save(role)? R.ok("角色数据新增成功"):R.error("角色数据新增失败");
    }

    @PostMapping("/updateRole")
    @ApiOperation(value = "角色数据更新")
    public   R  updateRole(  @RequestBody @ApiParam(value = "角色数据",required = true) @Validated({Update.class}) Role  role){
        Role byId = roleService.getById(role.getId());
//        role.setVersion(byId.getVersion());
        return   roleService.updateById(role)? R.ok("角色数据更新成功"):R.error("角色数据更新失败");
    }

    @PostMapping("/deleteRole/{id}")
    @ApiOperation(value = "角色数据删除")
    public R  deleteRole(@ApiParam(value = "用户ID",required = true) @PathVariable @Validated({Delete.class}) String id){
        return roleService.removeById(id)? R.ok("角色数据删除成功"):R.error("角色数据删除失败");

    }

    @GetMapping("/getRoleList/{page}/{size}")
    @ApiOperation(value = "角色数据列表")
    public R   getRoleList(@ApiParam(value = "当前页码", required = true) @PathVariable Integer page,
                              @ApiParam( value = "每页记录数", required = true) @PathVariable Integer size,
                              @ApiParam(value = "角色数据",required = false) Role  role){
        Page<Role> pages=new Page<>(page,size);
        try {
            IPage<Role> sysRole1 = roleService.getSysRole(pages, role);
            return  R.ok("获取角色数据列表成功").data("sysRole",sysRole1);
        }catch (Exception  e){
            e.printStackTrace();
            return  R.error("获取角色数据列表失败");
        }
    }
}
