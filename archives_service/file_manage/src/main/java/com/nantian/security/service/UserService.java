package com.nantian.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.security.User;

public interface UserService extends IService<User> {
 IPage<User> getSysUser(Page<User> page, User sysUser);

User findByUserName(String userId);
}
