package com.nantian.util.aop.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.natian.entity.operation.OperationLogging;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/27 15:05
 * @Description:
 */
@Mapper
public interface OperationLoggingMapper   extends BaseMapper<OperationLogging> {
}
