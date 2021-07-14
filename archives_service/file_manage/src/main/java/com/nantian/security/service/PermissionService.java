package com.nantian.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.security.Permission;


import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 10:30
 * @Description:
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> PermissionListByUser(String userId);
}
