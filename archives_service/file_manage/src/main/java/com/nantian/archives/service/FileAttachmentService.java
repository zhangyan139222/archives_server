package com.nantian.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.FileAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/7 14:18
 * @Description:
 */
public interface FileAttachmentService  extends IService<FileAttachment> {
    List<FileAttachment> getFileAttachmentList(String archivesId);
}
