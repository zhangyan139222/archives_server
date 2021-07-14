package com.nantian.security.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nantian.security.service.DeptService;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.R;
import com.natian.doamin.Update;
import com.natian.entity.security.Deptinfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"部门管理"})
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService deptService;

    @PostMapping("/saveDept")
    @ApiOperation(value = "部门数据新增")
    public R saveRole(@RequestBody @ApiParam(value = "部门数据",required = true) @Validated({Insert.class}) Deptinfo sysDeptinfo){
        return deptService.save(sysDeptinfo)? R.ok("部门数据新增成功"):R.error("部门数据新增失败");
    }

    @PostMapping("/updateDept")
    @ApiOperation(value = "部门数据更新")
    public   R  updateRole(  @RequestBody @ApiParam(value = "部门数据",required = true) @Validated({Update.class}) Deptinfo sysDeptinfo){
        Deptinfo byId = deptService.getById(sysDeptinfo.getDeptId());
//        sysDeptinfo.setVersion(byId.getVersion());
        return   deptService.updateById(sysDeptinfo)? R.ok("部门数据更新成功"):R.error("部门数据更新失败");
    }

    @PostMapping("/deleteDept/{id}")
    @ApiOperation(value = "部门数据删除")
    public R  deleteDept(@ApiParam(value = "用户ID",required = true) @PathVariable @Validated({Delete.class}) String id){
        return deptService.removeById(id)? R.ok("部门数据删除成功"):R.error("部门数据删除失败");

    }



    @GetMapping("/getDeptInfo")
    @ApiOperation(value = "部门用户数据列表")
    public  R   getDeptInfo(){
        try{
            List<Map<String, Object>> deptUser = deptService.getDeptUser();
          return   R.ok("获取用户信息列表成功").data("deptUser",deptUser);
        }catch (Exception  e){

           return   R.error("获取用户信息列表成功");

        }

    }

    @GetMapping("/getDeptList")
    @ApiOperation(value = "部门数据列表")
    public R   getDeptList(){
        try {
            List<Deptinfo> dept = deptService.list();
            return  R.ok("获取角色数据列表成功").data("dept",dept);
        }catch (Exception  e){
            e.printStackTrace();
            return  R.error("获取角色数据列表失败");
        }
    }







}
