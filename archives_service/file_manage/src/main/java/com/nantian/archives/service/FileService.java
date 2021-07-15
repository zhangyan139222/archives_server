package com.nantian.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.CurrentInstrument;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;
import java.util.HashMap;

public interface FileService extends IService<CurrentInstrument> {

    //    public  String    fileUpload(MultipartFile [] multipartFiles, HttpServletRequest  request);
    public HashMap<String, Object> fileUpload(MultipartFile multipartFile, HttpServletRequest request) throws UnknownHostException;

    public void downloadFile(String fileId, HttpServletRequest request, HttpServletResponse response);

    public int deleteFile(String fileId);

//    public   void fileOnlinePreview(String fileId,HttpServletResponse response) throws UnknownHostException;
}
