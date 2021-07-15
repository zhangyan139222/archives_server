package com.nantian.archives.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.UserFilePermission;
import org.springframework.stereotype.Service;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/23 16:29
 * @Description:
 */

public interface UserFilePermissionService extends IService<UserFilePermission> {

    Integer getFileIsPermission(String fileId);

    boolean saveUserFilePermission(JSONObject jsonObject);
}
