package com.nantian.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.FileOperationLogging;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/6 10:20
 * @Description:
 */
public interface FileOperationLoggingService   extends IService<FileOperationLogging> {
    List<FileOperationLogging> getOperationList( String fileId);
}
