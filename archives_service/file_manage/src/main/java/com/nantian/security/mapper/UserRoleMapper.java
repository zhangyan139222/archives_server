package com.nantian.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.natian.entity.security.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 10:33
 * @Description:
 */
@Mapper
public interface UserRoleMapper  extends BaseMapper<UserRole> {
   List<UserRole>  findByUid(String roleId);
}
