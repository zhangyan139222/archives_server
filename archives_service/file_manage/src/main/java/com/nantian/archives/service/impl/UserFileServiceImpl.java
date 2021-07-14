package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.CurrentInstrumentsMapper;
import com.nantian.archives.mapper.FileOperationLoggingMapper;
import com.nantian.archives.mapper.UserFileMapper;
import com.nantian.archives.mapper.WorkFlowMapper;
import com.nantian.archives.service.UserFileService;
import com.nantian.security.mapper.UserMapper;
import com.nantian.security.mapper.UserRoleMapper;
import com.nantian.security.service.UserRoleService;
import com.natian.doamin.R;
import com.natian.entity.archives.*;
import com.natian.entity.security.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/16 18:39
 * @Description:
 */
@Service
public class UserFileServiceImpl   extends ServiceImpl<UserFileMapper, UserFile>   implements UserFileService {
    @Resource
    private UserFileMapper userFileMapper;
    @Resource
    private UserMapper  userMapper;
    @Resource
    private FileOperationLoggingMapper  fileOperationLoggingMapper;

    @Override
    public Map<String,Object> getUserFileListByUser(String userId, UserFile  userFile, Integer page, Integer size) {
        Page<UserFile>  pages=null;
         if(page!=null && size!=null){
              pages=new Page<>(page,size);
         }
        IPage<UserFile> userFileIPage = null;
        List<UserFile>  userFiles=new ArrayList<>();
        User user = userMapper.selectById(userId);
        Map<String,Object>  map=new HashMap<>();
        if (user.getLevel()!=null && user.getLevel()==0) {
            QueryWrapper<UserFile> queryWrapper = new QueryWrapper<>();
             if(StringUtils.isNotEmpty(userFile.getTitle())){
                 queryWrapper.and(w->w.like("title",userFile.getTitle()).or().like("content",userFile.getTitle()));
             }
             if(StringUtils.isNotEmpty(userFile.getDeptNo())){
                 queryWrapper.like("dept_no",userFile.getDeptNo());
             }
             if(userFile.getAuditStatus()!=null){
                 queryWrapper.eq("audit_status",userFile.getAuditStatus());
             }
            if(StringUtils.isNotEmpty(userFile.getCreateTime())){
                queryWrapper.like("create_time",userFile.getCreateTime());
            }

            if(pages!=null){
                userFileIPage = userFileMapper.getUserFileOfPageByAdmin(pages,null);
                map.put("iPages",userFileIPage);

            }else{
                userFiles = userFileMapper.getUserFileByAdmin();
                map.put("iPages", userFiles);
            }
        }else if(user.getLevel()!=null && user.getLevel()==1){

        } else {     //其他普通用户获取自己申请数据列表

          if(pages!=null){
              userFileIPage = userFileMapper.getUserFileListByUserOfPage(pages,userId);
              map.put("iPages",userFileIPage);
          }else{
              userFiles = userFileMapper.getUserFileListByUser(userId);
              map.put("iPages",userFiles);
          }

        }
      /*  list=userFileIPage.getRecords();
        for(UserFile userFile1:list){
            User user1 = userMapper.findByUserName(userFile1.getExaminePersonId());
            List<String>  list1=new ArrayList<>();
            list1.add(user1.getUserId());
            list1.add(user1.getUserName());
            userFile1.setExaminePerson(list1);


        }
        userFileIPage.setRecords(list);*/
//        return userFileIPage;
        return   map;

    }

    @Override
    @Transactional
    public boolean saveUserFile(UserFile userFile) {
        int save = userFileMapper.insert(userFile);
        //新增档案日志
        FileOperationLogging fileOperationLogging=new FileOperationLogging();
        fileOperationLogging.setFileId(userFile.getId());
        fileOperationLogging.setUserId(userFile.getUserId());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
        fileOperationLogging.setOperName("新增档案查阅申请");
        fileOperationLogging.setOperType(2);
        int save2 = fileOperationLoggingMapper.insert(fileOperationLogging);
        if(save!=0 && save2!=0){
            return  true;
        }else {
            return false;
        }

    }

    @Override
    @Transactional
    public boolean updateUserFile(UserFile userFile) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
         if(userFile.getAuditStatus()==2){     //审核不通过的数据修改后变成待审核
             userFile.setAuditStatus(0);
         }
        //更新档案查阅申请数据
        int save = userFileMapper.updateById(userFile);
        //新增档案日志
        FileOperationLogging fileOperationLogging=new FileOperationLogging();
        fileOperationLogging.setFileId(userFile.getId());
        fileOperationLogging.setUserId(userFile.getUserId());

        fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
        fileOperationLogging.setOperName("修改档案查阅申请");
        fileOperationLogging.setOperType(2);
        int save1 = fileOperationLoggingMapper.insert(fileOperationLogging);
        if(save!=0 && save1!=0){
            return  true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean examineUserFile(String id, Integer auditStatus, String auditMind, String userId) {
        //档案查阅申请审核
        UserFile  userFile=new UserFile();
        userFile.setId(id);
        userFile.setAuditStatus(auditStatus);
        userFile.setReason(auditMind);
        SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userFile.setAuditTime(simpleDateFormat.format(new Date()));
        int save = userFileMapper.updateById(userFile);
        FileOperationLogging   fileOperationLogging=new FileOperationLogging();
        fileOperationLogging.setFileId(id);
        fileOperationLogging.setUserId(userId);
        fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
        if(auditStatus!=null &&  auditStatus==1){
            fileOperationLogging.setOperName("档案查阅申请审核通过");
        }
        if(auditStatus!=null &&  auditStatus==2){
            fileOperationLogging.setOperName("档案查阅申请审核不通过");
            fileOperationLogging.setOperDesc(auditMind);
        }
        fileOperationLogging.setOperType(2);
        int save1 = fileOperationLoggingMapper.insert(fileOperationLogging);

        if(save!=0  &&  save1!=0){
            return true;
        }else{
            return false;
        }

    }


    @Override
    public  Map<String, Object> getAllTotalByAudit() {
        return userFileMapper.getAllTotalByAudit();
    }

    @Override
    public  Map<String, Object>getAllTotalByAuditByMonth() {
        return userFileMapper.getAllTotalByAuditByMonth();
    }
}
