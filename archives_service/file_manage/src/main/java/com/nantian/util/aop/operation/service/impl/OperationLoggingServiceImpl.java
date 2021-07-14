package com.nantian.util.aop.operation.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.util.aop.operation.mapper.OperationLoggingMapper;
import com.nantian.util.aop.operation.service.OperationLoggingService;
import com.natian.entity.operation.OperationLogging;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/27 15:05
 * @Description:
 */
@Service
public class OperationLoggingServiceImpl   extends ServiceImpl<OperationLoggingMapper, OperationLogging> implements OperationLoggingService {
    @Resource
    private OperationLoggingMapper operationLoggingMapper;
}
