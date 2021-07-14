package com.nantian.archives.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.FileOperationLoggingMapper;
import com.nantian.archives.mapper.UserFileMapper;
import com.nantian.archives.mapper.UserFilePermissionMapper;
import com.nantian.archives.service.UserFilePermissionService;
import com.nantian.archives.service.UserFileService;
import com.natian.doamin.R;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.FileOperationLogging;
import com.natian.entity.archives.UserFile;
import com.natian.entity.archives.UserFilePermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/23 16:30
 * @Description:
 */
@Service
public class UserFilePermissionServiceImpl   extends ServiceImpl<UserFilePermissionMapper, UserFilePermission>
        implements UserFilePermissionService {
    @Resource
    private    UserFilePermissionMapper  userFilePermissionMapper;

    @Resource
    private UserFileMapper  userFileMapper;
    @Resource
    private FileOperationLoggingMapper   fileOperationLoggingMapper;

    @Override
    public Integer getFileIsPermission(String fileId) {
        return userFilePermissionMapper.getFileIsPermission(fileId);
    }

    @Override
    public boolean saveUserFilePermission(JSONObject jsonObject) {
        UserFilePermission  userFilePermission=new UserFilePermission();
        int result=0;
        String applyId= jsonObject.get("applyId").toString();
//        List<CurrentInstrument> currentInstruments = (List<CurrentInstrument>) jsonObject.get("currentInstruments");
        List<String> currentInstruments=(List<String>) jsonObject.get("fileIds");    //授权数据ID

        String saveTime = jsonObject.get("endTime").toString();
        String userId=jsonObject.get("userId").toString();
        String examinId=jsonObject.get("userId").toString();
        List<UserFilePermission> userFilePermissions=new ArrayList<>();
        for (String  str:currentInstruments){
//            String jsonString = JSONObject.toJSONString(object);
//            CurrentInstrument currentInstrument = JSONObject.parseObject(jsonString, CurrentInstrument.class);
            Integer fileIsPermission = userFilePermissionMapper.getFileIsPermission(str);
            if(fileIsPermission==0){    //对于未授权的数据才能授权
                userFilePermission.setFileId(str);
                userFilePermission.setPermissionId(applyId);
                userFilePermission.setId(UUID.randomUUID().toString());
                userFilePermission.setEndTime(saveTime);
                userFilePermissions.add(userFilePermission);
                userFilePermission.setAuditStatus(1);
                userFilePermission.setUserId(userId);
                int save = userFilePermissionMapper.insert(userFilePermission);
                if(save!=0){
                    result++;
                }
            }
        }
        int up=0;
        if(result!=0){
            UserFile userFile=new UserFile();
            userFile.setAuditStatus(3);
            userFile.setId(applyId);
             up = userFileMapper.updateById(userFile);
        }
        if(result==userFilePermissions.size() && up!=0 ){
            //新增档案日志
            FileOperationLogging fileOperationLogging=new FileOperationLogging();
            fileOperationLogging.setFileId(applyId);
            fileOperationLogging.setUserId(userId);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
            fileOperationLogging.setOperName("档案查阅申请已授权");
            fileOperationLogging.setOperType(2);
            fileOperationLoggingMapper.insert(fileOperationLogging);
            return true;
        }else{
            return false;
        }
    }
}
