package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.AuditRecords;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/6 14:55
 * @Description:
 */
@Mapper
public interface AuditRecordsMapper  extends BaseMapper<AuditRecords> {
}
