package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.WorkFlowMapper;
import com.nantian.archives.service.WorkFlowService;
import com.natian.entity.archives.WorkFlow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/25 16:21
 * @Description:
 */
@Service
public class WorkFlowServiceImpl  extends ServiceImpl<WorkFlowMapper, WorkFlow>   implements WorkFlowService {

    @Resource
    private   WorkFlowMapper   workFlowMapper;


    @Override
      public WorkFlow getWorkFlow( String flowId){
        return   workFlowMapper.getWorkFlow(flowId);
    }
}
