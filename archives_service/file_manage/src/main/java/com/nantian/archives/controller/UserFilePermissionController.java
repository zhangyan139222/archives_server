package com.nantian.archives.controller;


import com.alibaba.fastjson.JSONObject;
import com.nantian.archives.service.UserFilePermissionService;
import com.nantian.archives.service.UserFileService;
import com.nantian.archives.service.WorkFlowService;
import com.natian.doamin.R;
import com.natian.entity.archives.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/23 17:35
 * @Description:
 */
@RestController
@RequestMapping("/userFilePermission")
@Api(tags ={"数据审核"})
public class UserFilePermissionController {

    @Resource
    private UserFilePermissionService userFilePermissionService;

    /**
     * 数据授权
     * @param
     * @return
     */
    @ApiOperation(value="数据授权")
    @PostMapping("/saveUserFilePermission")
    public R saveUserFilePermission(@RequestBody JSONObject jsonObject ){
       return   userFilePermissionService.saveUserFilePermission(jsonObject)? R.ok("数据授权成功"):R.ok("数据授权失败");
    }



    @ApiOperation(value="数据是否已操作")
    @PostMapping("/getFileIsPermission/{id}")
    public  R   getFileIsPermission( @ApiParam(value="ID",required = true)  @PathVariable String id){
        UserFilePermission byId = userFilePermissionService.getById(id);
        if(byId.getAuditStatus()==1){
            return   R.ok("数据已经授权").data("AuditStatus",byId.getAuditStatus());
        }else {
            return  R.ok("").data("AuditStatus",byId.getAuditStatus());
        }
    }


}
