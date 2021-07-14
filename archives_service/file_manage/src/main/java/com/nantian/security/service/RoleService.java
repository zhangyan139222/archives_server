package com.nantian.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.security.Role;

import java.util.List;


public interface RoleService  extends IService<Role> {
    IPage<Role> getSysRole(Page<Role> page, Role role);
    List<Role> findByIds(List<String> ids);
}
