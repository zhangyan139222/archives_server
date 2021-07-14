package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.WorkFlowMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/26 9:30
 * @Description:
 */
@Mapper
public interface WorkFlowMessageMpper   extends BaseMapper<WorkFlowMessage> {
}
