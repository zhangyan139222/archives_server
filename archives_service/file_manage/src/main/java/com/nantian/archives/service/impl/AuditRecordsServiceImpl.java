package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.AuditRecordsMapper;
import com.nantian.archives.service.AuditRecordsService;
import com.natian.entity.archives.AuditRecords;
import org.springframework.stereotype.Service;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/6 14:57
 * @Description:
 */
@Service
public class AuditRecordsServiceImpl   extends ServiceImpl<AuditRecordsMapper, AuditRecords>  implements AuditRecordsService {
}
