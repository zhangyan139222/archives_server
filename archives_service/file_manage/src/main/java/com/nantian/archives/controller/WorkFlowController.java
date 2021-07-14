package com.nantian.archives.controller;

import com.nantian.archives.service.WorkFlowService;
import com.natian.doamin.R;
import com.natian.entity.archives.WorkFlow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/25 16:36
 * @Description:
 */
@RestController
@RequestMapping("/workFlow")
@Api(tags ={"审批流程"})
public class WorkFlowController {

    @Resource
    private WorkFlowService   workFlowService;


    @ApiOperation(value="数据审批")
    @GetMapping("/getWorkFlow/{flowId}")
    public R getWorkFlow(@ApiParam(value="流程ID",required = true)  @PathVariable String   flowId){
        try{
            WorkFlow workFlow = workFlowService.getWorkFlow(flowId);
           return R.ok("审批流程获取成功").data("workFlow",workFlow);
        }catch(Exception  e){
            return  R.ok("审批流程获取失败");
        }


    }
}
