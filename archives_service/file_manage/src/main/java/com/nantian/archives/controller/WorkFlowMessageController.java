package com.nantian.archives.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nantian.archives.service.WorkFlowMessageService;
import com.natian.doamin.R;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.WorkFlowMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/26 10:11
 * @Description:
 */
@RestController
@RequestMapping("/CurrentInstruments")
@Api(value="/CurrentInstruments",tags ={"代办通知"})
public class WorkFlowMessageController {

    private WorkFlowMessageService   workFlowMessageService;


    @ApiOperation(value="获取代办消息")
    @GetMapping("/getWorkFlowMessage")
    public R getWorkFlowMessage(@ApiParam(value = "id",required = true)@PathVariable String userId,
                                @ApiParam(value="当前页码",required = true)@PathVariable Long page,
                                @ApiParam(value="每页记录数",required = true)@PathVariable Long  size,
                                @ApiParam(value="档案Id",required = true)@PathVariable String  filed){

        try{
            IPage<WorkFlowMessage> workFlowMessage = workFlowMessageService.getWorkFlowMessage(userId, page, size, filed);
            return   R.ok("获取已授权数据列表成功").data("workFlowMessage",workFlowMessage);
        }catch (Exception e){
            return R.error("获取已授权数据列表失败");
        }

    }
}
