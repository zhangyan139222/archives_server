package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.natian.entity.archives.WorkFlow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/25 16:20
 * @Description:
 */
@Mapper
public interface WorkFlowMapper  extends BaseMapper<WorkFlow> {

    @Select("select process_status  AS processStatus, permission_message  AS permissionMessage,node_id  AS nodeId," +
            " node_status  AS nodeStatus from   file_workflow  where  folw_id=#{flowId}")
    WorkFlow getWorkFlow(@Param("flowId") String flowId);
}
