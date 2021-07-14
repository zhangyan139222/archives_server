package com.nantian.archives.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.natian.entity.archives.WorkFlowMessage;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/26 9:31
 * @Description:
 */
public interface WorkFlowMessageService    extends IService<WorkFlowMessage> {

    IPage<WorkFlowMessage> getWorkFlowMessage(String userId,Long page,Long  size,String  filed);
}
