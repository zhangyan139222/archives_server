package com.nantian.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.security.mapper.UserMapper;
import com.nantian.security.service.UserService;
import com.natian.entity.security.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User>  implements UserService {
    @Resource
    private   UserMapper  userMapper;
    @Override
    public IPage<User> getSysUser(Page<User> page, User sysUser) {
        Wrapper<User>  wrapper=new Wrapper<User>() {
            @Override
            public User getEntity() {
                return sysUser;
            }

            @Override
            public MergeSegments getExpression() { return null; }

            @Override
            public void clear() { }

            @Override
            public String getSqlSegment() {
                StringBuffer sql=new StringBuffer();
                sql.append(" where  1=1");
                if(StringUtils.isNotEmpty(sysUser.getUserId())){
                    sql.append(" and user_id= '").append(sysUser.getUserId()).append("'");
                }
                if(StringUtils.isNotEmpty(sysUser.getUserName())){
                   sql.append(" and  user_name like'%").append(sysUser.getUserName()).append("%'");
                }
                if(StringUtils.isNotEmpty(sysUser.getDeptId())){
                    sql.append(" and a.dept_id= '").append(sysUser.getDeptId()).append("'");
                }
                return sql.toString();
            }
        };

        return userMapper.getSysUser(page,wrapper) ;
    }

    @Override
    public User findByUserName(String userId) {
        return userMapper.findByUserName(userId) ;
    }
}
