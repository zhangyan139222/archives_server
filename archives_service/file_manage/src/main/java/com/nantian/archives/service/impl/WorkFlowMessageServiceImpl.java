package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.WorkFlowMapper;
import com.nantian.archives.mapper.WorkFlowMessageMpper;
import com.nantian.archives.service.WorkFlowMessageService;
import com.nantian.archives.service.WorkFlowService;
import com.natian.entity.archives.WorkFlowMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/26 9:31
 * @Description:
 */
@Service
public class WorkFlowMessageServiceImpl    extends ServiceImpl<WorkFlowMessageMpper, WorkFlowMessage> implements WorkFlowMessageService {
    @Resource
    private WorkFlowMessageMpper   workFlowMessageMpper;


    @Override
    public IPage<WorkFlowMessage> getWorkFlowMessage(String userId, Long page, Long size, String filed) {

        Page<WorkFlowMessage>  pages=new Page<>(page,size);
        QueryWrapper<WorkFlowMessage>  queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(userId)){
            queryWrapper.eq("user_id",userId);
        }
        if(StringUtils.isNotEmpty(filed)){
            queryWrapper.eq("filed",filed);
        }
        return workFlowMessageMpper.selectPage(pages,queryWrapper);
    }
}
