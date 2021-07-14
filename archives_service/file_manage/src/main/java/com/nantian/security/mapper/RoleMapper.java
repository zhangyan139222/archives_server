package com.nantian.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.natian.entity.security.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper  extends BaseMapper<Role> {
  @Select("select *  from sys_role where role_id in #{ids}")
  List<Role>  findByIds(  @Param("ids") List<String> ids);
}
