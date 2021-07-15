package com.nantian.archives.controller;


import com.nantian.archives.service.CurrentInstrumentsService;
import com.nantian.archives.service.FileService;
import com.natian.doamin.R;
import com.natian.entity.archives.CurrentInstrument;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

@RestController
@RequestMapping("file")
@Api(tags = {"附件管理"})
public class FileController {
    @Resource
    private FileService   fileService;
    @Resource
    private CurrentInstrumentsService currentInstrumentsService;


/*    @ApiOperation("文件上传")
    @PostMapping("/uploadFile")
    public R uploadFile(@ApiParam(value="上传附件") MultipartFile[] multipartFiles, HttpServletRequest  request) {
        String  fileUrl=fileService.fileUpload(multipartFiles,request);
        if(fileUrl.equals("文件上传失败")){
            return R.error("文件上传失败");
        }else if(fileUrl.equals("请选择上传的文件")){
            return R.error("请选择上传的文件");
        }else{
            return  R.ok("文件上传成功").data("url",fileUrl);
        }

    }*/


    @ApiOperation("文件上传")
    @PostMapping("/uploadFile")
    public R uploadFile(@ApiParam(value = "上传附件") MultipartFile file, HttpServletRequest request) {
        HashMap<String, Object> map = null;
        try {
            map = fileService.fileUpload(file, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String message = (String) map.get("message");
        return R.ok(message).data(map);

    }

    @GetMapping("/downloadFile/{id}")
    @ApiOperation("文件下载")
    public void downloadFile(@ApiParam(value = "通过Id获取文件URL", required = true) @PathVariable String id, HttpServletRequest request,
                             HttpServletResponse response) {
        fileService.downloadFile(id, request, response);
    }


    @ApiOperation("文件删除")
    @PostMapping("deleteFile/{id}")
    public R deleteFile(@ApiParam(value = "档案附件ID") @PathVariable String id) {
        int result = fileService.deleteFile(id);
        return result == 0 ? R.ok("文件删除失败") : R.error("文件删除成功");
    }


    @ApiOperation("文件在线预览")
    @GetMapping("/preview/{id}")
    public   void fileOnlinePreview(@ApiParam(value="通过Id获取文件URL") @PathVariable String id,HttpServletResponse response){
//        try {
//            fileService.fileOnlinePreview(id,response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    @ApiOperation("获取文件地址")
    @GetMapping("/getFileUrl/{fileId}")
    public R getFileUrl(@ApiParam(value = "通过Id获取文件URL") @PathVariable String fileId) {
        try {
            CurrentInstrument byId = currentInstrumentsService.getById(fileId);
            String fileUrl = byId.getStorageAddress();
            return R.ok("获取文件地址成功").data("fileUrl", fileUrl);
        } catch (Exception e) {
            return R.error("获取文件地址失败");
        }

    }

}
