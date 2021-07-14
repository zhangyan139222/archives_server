package com.nantian.security.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.natian.entity.security.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select a.*,b. dept_name deptName from sys_user a left join sys_deptinfo b on a.dept_id=b.dept_id ${wrapper.getSqlSegment}")
    IPage<User> getSysUser(Page<User> page, @Param("wrapper") Wrapper wrapper);

    @Select("select *   from sys_user where user_id=#{userId}")
    User findByUserName(@Param("userId") String userId);


    @Select("select  su.*,sr.role_id,sr.role_name   from  sys_user su  left  join   sys_user_role  sur  on su.user_id=sur.user_id " +
            "left  join  sys_role     sr on    sr.role_id=sur.role_id   WHERE  su.user_id=#{userId}")
    List<User> getUserRole(@Param("userId") String userId);
}
