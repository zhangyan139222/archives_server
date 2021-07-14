package com.nantian.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.security.mapper.RoleMapper;
import com.nantian.security.service.RoleService;
import com.natian.entity.security.Role;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl   extends ServiceImpl<RoleMapper, Role>  implements RoleService {
    @Resource
    private   RoleMapper  roleMapper;
    @Override
    public IPage<Role> getSysRole(Page<Role> page, Role role) {
        QueryWrapper<Role>  queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(role.getRoleName())){
              queryWrapper.like("role_name",role.getRoleName());
        }
        IPage<Role>  iPage =roleMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<Role> findByIds(List<String> ids) {
        return null;
    }
}
