package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.UserFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/16 18:38
 * @Description:
 */
@Mapper
public interface UserFileMapper  extends BaseMapper<UserFile> {


    @Select("<script>select distinct duf.*,dufp.end_time  AS  endTime from  d_user_file  duf left join d_user_file_permission  " +
            "dufp on duf.id=dufp.permission_id  <where> <when test='userId !=null'>    a.user_id=#{userId} </when>" +
            "<when test='applyUser !=null'>    user_name like CONCAT('%',#{applyUser},'%')</when></where></script>")
    IPage<UserFile>getUserFileListByUserOfPage(Page<UserFile> pages, @Param(value = "userId") String userId);


    @Select("select distinct duf.*,dufp.end_time  AS endTime  from  d_user_file  duf left join d_user_file_permission  dufp on duf.id=dufp.permission_id   where    a.user_id=#{userId}")
    List<UserFile>getUserFileListByUser( @Param(value = "userId") String userId);

    @Select("select distinct duf.*,dufp.end_time  AS endTime  from  d_user_file  duf left join d_user_file_permission  dufp on duf.id=dufp.permission_id ")
    List<UserFile>  getUserFileByAdmin();

    @Select("<script> select distinct  duf.*,dufp.end_time  AS  endTime from  d_user_file  duf left join d_user_file_permission " +
            " dufp on duf.id=dufp.permission_id <where><when test='applyUser !=null'>  user_name like CONCAT('%',#{applyUser},'%')</when></where> </script>")
    IPage<UserFile>  getUserFileOfPageByAdmin(@Param("pages") Page<UserFile> pages,@Param("applyUser") String applyUser );



    @Select("select  duf.*  from  d_user_file  duf  where duf.audit_status=4  and   date_format(duf.update_time,'%Y-%m-%d')=curdate()")
    List<UserFile>  getUserFileByAdminByDay();


    @Select("select  duf.*  from  d_user_file  duf  where   duf.user_id=#{userId}   duf.audit_status=3  and   date_format(duf.update_time,'%Y-%m-%d')=curdate()")
    List<UserFile>  getUserFileByUserByDay(@Param("userId")String userId);



   @Select(" SELECT  COUNT(*) AS Total, SUM(case when a.audit_status=3 then 1 else 0 end) as pass, " +
           "SUM(case when a.audit_status <>3 then 1 else 0 end) as nopass FROM d_user_file a ")
   Map<String, Object> getAllTotalByAudit();

    @Select(" SELECT DATE_FORMAT(a.update_time, '%Y-%m') AS monthly,COUNT(*) AS Total, SUM(case when a.audit_status=3 then 1 else 0 end) as pass, " +
            "SUM(case when a.audit_status a.audit_status <>3  then 1 else 0 end) as nopass FROM d_user_file a " +
            "GROUP BY DATE_FORMAT(a.update_time, '%Y-%m') ")
    Map<String, Object> getAllTotalByAuditByMonth();
}
