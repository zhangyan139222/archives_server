package com.nantian.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.security.UserRole;

import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 10:34
 * @Description:
 */
public interface UserRoleService  extends IService<UserRole> {
    List<UserRole> findByUid(String roleId);
}
