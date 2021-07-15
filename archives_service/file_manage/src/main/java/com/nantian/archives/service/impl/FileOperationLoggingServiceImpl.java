package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.FileOperationLoggingMapper;
import com.nantian.archives.service.FileOperationLoggingService;
import com.natian.entity.archives.FileOperationLogging;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/6 10:21
 * @Description:
 */

@Service
public class FileOperationLoggingServiceImpl   extends ServiceImpl<FileOperationLoggingMapper, FileOperationLogging>
        implements FileOperationLoggingService {
    @Resource
    private FileOperationLoggingMapper fileOperationLoggingMapper;


    /**
     * 获取档案操作对应日志
     * @param fileId
     * @return
     */

    @Override
    public List<FileOperationLogging> getOperationList(String fileId) {
        try {
            return fileOperationLoggingMapper.getOperationList(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }

    }
}