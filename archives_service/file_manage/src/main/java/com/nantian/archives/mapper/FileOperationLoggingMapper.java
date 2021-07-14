package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.natian.entity.archives.FileOperationLogging;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/6 10:19
 * @Description:
 */

@Mapper
public interface FileOperationLoggingMapper extends BaseMapper<FileOperationLogging> {


    @Select("select  dfol.*,su.monicker AS  monicker     from  d_file_operation_logging   dfol   left   join sys_user  su   on dfol.user_id=su.user_id   where dfol.file_id=#{fileId} order by  dfol.finish_time desc")
    List<FileOperationLogging> getOperationList( @Param("fileId") String fileId);
}
