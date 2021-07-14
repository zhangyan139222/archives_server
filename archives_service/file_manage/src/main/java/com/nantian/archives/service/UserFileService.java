package com.nantian.archives.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.UserFile;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/16 18:39
 * @Description:
 */
public interface UserFileService   extends IService<UserFile> {
    public Map<String,Object> getUserFileListByUser(String uesrId, UserFile userFile, Integer page, Integer size);


    boolean  saveUserFile(UserFile  userFile);

    boolean updateUserFile(UserFile  userFile);

    boolean  examineUserFile(String id,Integer auditStatus,String  reason,String  userId);



    Map<String, Object> getAllTotalByAudit();

    Map<String, Object>  getAllTotalByAuditByMonth();



}
