package com.nantian.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.security.mapper.RoleFunctionMapper;
import com.nantian.security.service.RoleFunctionService;
import com.natian.entity.security.RoleFunction;
import org.springframework.stereotype.Service;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 10:39
 * @Description:
 */
@Service
public class RoleFunctionServiceImpl  extends ServiceImpl<RoleFunctionMapper, RoleFunction> implements RoleFunctionService {
}
