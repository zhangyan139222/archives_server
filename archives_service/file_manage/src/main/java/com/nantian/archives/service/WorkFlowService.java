package com.nantian.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.WorkFlow;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/25 16:21
 * @Description:
 */
public interface WorkFlowService  extends IService<WorkFlow> {
   public WorkFlow getWorkFlow( String flowId);
}
