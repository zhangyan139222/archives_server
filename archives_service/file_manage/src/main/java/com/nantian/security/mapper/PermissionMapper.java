package com.nantian.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.natian.entity.security.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 10:28
 * @Description:
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
   @Select("select a.id  AS id,a.pwd AS pwd,a.user_id  AS userId,a.user_name  AS userName, a.role_id  AS  roleId,a.function_id AS functionId," +
           "a.function_url  AS functionUrl ,a.function_name  AS  functionName,a.function_order  AS functionOrder,a.parent_id AS parentId," +
           "a.icond  AS  icond,a.function_type   AS  functionType from(select  su.id,su.user_id,su.pwd ,su.user_name,sur.role_id,srf.function_id," +
           "sf.function_url,sf.function_type,sf.function_name,sf.function_order,sf.parent_id,sf.icond from  sys_user   su " +
           "inner join  sys_user_role   sur  on su.user_id=sur.user_id " +
           "inner join  sys_role_function  srf  on  sur.role_id=srf.role_id " +
           "inner join  sys_function   sf on   srf.function_id=sf.function_id" +
           " where su.user_id=#{userId}) a ")
    List<Permission>   PermissionListByUser(@Param(value="userId") String userId);
}
