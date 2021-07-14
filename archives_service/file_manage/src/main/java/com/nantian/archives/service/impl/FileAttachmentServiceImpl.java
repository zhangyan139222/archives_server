package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.FileAttachmentMapper;
import com.nantian.archives.service.FileAttachmentService;
import com.natian.entity.archives.FileAttachment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/7 14:19
 * @Description:
 */
@Service
public class FileAttachmentServiceImpl  extends ServiceImpl<FileAttachmentMapper, FileAttachment>  implements FileAttachmentService {
   @Resource
   private   FileAttachmentMapper  fileAttachmentMapper;
    @Override
    public List<FileAttachment> getFileAttachmentList(String archivesId) {
        return fileAttachmentMapper.getFileAttachmentList(archivesId);
    }
}
