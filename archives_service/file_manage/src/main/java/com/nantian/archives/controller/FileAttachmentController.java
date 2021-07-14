package com.nantian.archives.controller;

import com.nantian.archives.service.FileAttachmentService;
import com.natian.doamin.R;
import com.natian.entity.archives.FileAttachment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/7 15:15
 * @Description:
 */
@RestController
@RequestMapping("fileAttachment")
@Api(tags ={"电子档案附件管理"})
public class FileAttachmentController {
    @Resource
    private FileAttachmentService   fileAttachmentService;

    @ApiOperation(value="查询所有的附件")
    @GetMapping("/getFileList/{archivesId}")
    public R getFileAttachmentList(@ApiParam(value="档案ID",required = true) @PathVariable String archivesId){
        try {
            List<FileAttachment> fileAttachmentList = fileAttachmentService.getFileAttachmentList(archivesId);
          return   R.ok("成功获取所有的附件").data("fileAttachmentList",fileAttachmentList);
        }catch (Exception  e){
            e.printStackTrace();
            return   R.error("获取附件失败");

        }
    }
}
