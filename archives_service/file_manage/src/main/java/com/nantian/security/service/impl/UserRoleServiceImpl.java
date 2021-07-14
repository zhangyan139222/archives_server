package com.nantian.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.security.mapper.UserRoleMapper;
import com.nantian.security.service.UserRoleService;
import com.natian.entity.security.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 10:36
 * @Description:
 */
@Service
public class UserRoleServiceImpl  extends ServiceImpl<UserRoleMapper,UserRole>  implements UserRoleService {
    @Resource
    private   UserRoleMapper  userRoleMapper;
    @Override
    public List<UserRole> findByUid(String roleId) {
        return userRoleMapper.findByUid(roleId);
    }
}
