package com.nantian.archives.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.nantian.archives.service.CurrentInstrumentsService;
import com.nantian.archives.service.UserFilePermissionService;
import com.nantian.archives.service.UserFileService;
import com.nantian.archives.service.WorkFlowService;
import com.natian.doamin.Insert;
import com.natian.doamin.R;
import com.natian.entity.archives.*;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Resource
    private UserFileService userFileService;
    @Resource
    private WorkFlowService  workFlowService;


    /**
     * 数据授权
     * @param
     * @return
     */
    @ApiOperation(value="数据授权")
    @PostMapping("/saveUserFilePermission")
    public R saveUserFilePermission(@RequestBody JSONObject jsonObject ){
      /*  UserFilePermission  userFilePermission=new UserFilePermission();
        int result=0;
        String applyId= jsonObject.get("applyId").toString();
//        List<CurrentInstrument> currentInstruments = (List<CurrentInstrument>) jsonObject.get("currentInstruments");
          List<String>  currentInstruments=(List<String>) jsonObject.get("fileIds");    //授权数据ID

        String saveTime = jsonObject.get("endTime").toString();
        String userId=jsonObject.get("userId").toString();
        String examinId=jsonObject.get("userId").toString();
        List<UserFilePermission> userFilePermissions=new ArrayList<>();
        for (String  str:currentInstruments){
//            String jsonString = JSONObject.toJSONString(object);
//            CurrentInstrument currentInstrument = JSONObject.parseObject(jsonString, CurrentInstrument.class);
            Integer fileIsPermission = userFilePermissionService.getFileIsPermission(str);
            if(fileIsPermission==0){    //对于未授权的数据才能授权
                userFilePermission.setFileId(str);
                userFilePermission.setPermissionId(applyId);
                userFilePermission.setId(UUID.randomUUID().toString());
                userFilePermission.setEndTime(saveTime);
                userFilePermissions.add(userFilePermission);
                userFilePermission.setAuditStatus(1);
                userFilePermission.setUserId(userId);
                boolean save = userFilePermissionService.save(userFilePermission);
                if(save){
                    result++;
                }
            }
       }
      boolean  up=true;
        if(result!=0){
            UserFile  userFile=new UserFile();
            userFile.setAuditStatus(3);
            userFile.setId(applyId);
             up = userFileService.updateById(userFile);
        }
       if(result==userFilePermissions.size() && up ){
          return   R.ok("数据授权成功");
       }else{
           return   R.ok("数据授权失败");
       }*/
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
