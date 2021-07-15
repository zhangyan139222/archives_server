package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.CurrentInstrumentsMapper;
import com.nantian.archives.mapper.FileAttachmentMapper;
import com.nantian.archives.mapper.FileMapper;
import com.nantian.archives.service.FileService;
import com.nantian.archives.util.FileUtils;
import com.nantian.config.FileConfig;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.FileAttachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
@Slf4j
public class FileServiceImpl   extends ServiceImpl<FileMapper, CurrentInstrument> implements FileService {
//    @Resource
//    private DocumentConverter converter;  //用于转换
    @Autowired
    private FileConfig fileConfig;
    @Resource
    private FileMapper fileMapper;
    @Resource
    private CurrentInstrumentsMapper currentInstrumentsMapper;
    @Resource
    private FileAttachmentMapper  fileAttachmentMapper;

    @Value("${file.server.uploadVirtual}")
    private String virtualPath;

    /**
     * 文件上传
     * @param
     * @param request
     * @return  上传文件的地址
     */
   /* @Override
    public String fileUpload(MultipartFile[] multipartFiles, HttpServletRequest request) {
        if(multipartFiles!=null && multipartFiles.length!=0){
            //项目根目录的绝对路径
            String realPath = fileConfig.multipartConfigElement().getLocation() ;
            //存放上传文件的文件夹
            File file =new File(realPath);
            if(!file.isDirectory()){
                file.mkdirs();
            }
            for(int i=0;i<multipartFiles.length;i++){
                //获取原始文件的名称
                String oldName=multipartFiles[i].getOriginalFilename();
                //获取新的文件名 (原文件名+时间戳+文件类型)
                SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
                String  newName=oldName.substring(0,oldName.lastIndexOf("."))+simpleDateFormat.format(new Date())+
                        oldName.substring(oldName.lastIndexOf("."));
                try{
                    //构建真实的文件路径
                    File  newFile=new  File(file.getAbsolutePath()+File.separator+newName);
                    //转存文件到指定路径，如果文件名重复，将会覆盖掉之前的文件,文件上传到绝对路径
                    multipartFiles[i].transferTo(newFile);
                    String  filePath=realPath+"/"+newName;
                    return   filePath;
                }catch (Exception  e){
                    e.printStackTrace();
                }
            }
            return "文件上传失败";
        }else{
            return  "请选择上传的文件";
        }
    }*/

    /**
     * 文件上传
     * @param multipartFile
     * @param request
     * @return
     */

    @Override
    public HashMap<String, Object> fileUpload(MultipartFile multipartFile, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        if (multipartFile != null) {
            //项目根目录的绝对路径
            String realPath = fileConfig.multipartConfigElement().getLocation();
            //存放上传文件的文件夹
            File file = new File(realPath);
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            //获取原始文件的名称
            String oldName = multipartFile.getOriginalFilename();
            //获取新的文件名 (原文件名+时间戳+文件类型)
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String newName = oldName.substring(0, oldName.lastIndexOf(".")) + simpleDateFormat.format(new Date()) +
                    oldName.substring(oldName.lastIndexOf("."));
            try {
                //构建真实的文件路径
                File newFile = new File(file.getAbsolutePath() + File.separator + newName);
                //转存文件到指定路径，如果文件名重复，将会覆盖掉之前的文件,文件上传到绝对路径
                multipartFile.transferTo(newFile);
                String Path = realPath + newName;
                String filePath = virtualPath + newName;
                map.put("attachamentTitle", oldName);
                map.put("fileUrl", Path);
                map.put("storageAddress", filePath);
                map.put("message", "文件上传成功");
            } catch (Exception e) {
                map.put("message", "文件上传失败");
                e.printStackTrace();
            }

        } else {
            map.put("message", "请选择上传的文件");
        }
        return map;
    }


    /**
     * 文件下载
     *
     * @param id
     * @param request
     * @param response
     */
    @Override
    public void downloadFile(String id, HttpServletRequest request, HttpServletResponse response) {
        FileAttachment fileAttachment = fileAttachmentMapper.selectById(id);
        String filePath = fileAttachment.getFileUrl();
        //文件名称
        String fileName = fileAttachment.getAttachamentTitle();
        if (StringUtils.isNotEmpty(filePath)) {
            try {
                response.setCharacterEncoding("utf-8");
                response.setContentType("multipart/form-data");
                response.setHeader("Content-Disposition",
                        "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
                FileUtils.writeBytes(filePath, response.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            log.error("文件路径不能为空");
        }

    }

    @Override
    public int deleteFile(String id) {
//        CurrentInstrument currentInstrument = fileMapper.selectById(fileId);
//        String  fileName=currentInstrument.getFileUrl();
//        File  file=new File(fileName);
//        if(file.exists()){
//            file.delete();
//            return true;
//        }else{
//            return false;
//        }
        /*CurrentInstrument currentInstrument = currentInstrumentsMapper.selectById(fileId);
        currentInstrument.setFileUrl("");
        currentInstrument.setStorageAddress("");
        currentInstrument.setAttachamentTitle("");
        int result = currentInstrumentsMapper.updateById(currentInstrument);*/
        int result= fileAttachmentMapper.deleteById(id);
        return result;

    }

 /*   @Override
    public void fileOnlinePreview(String id,HttpServletResponse response){
        FileAttachment fileAttachment = fileAttachmentMapper.selectById(id);
        String filePath=fileAttachment.getFileUrl();
        //获取文件名称
        String  fileName=fileAttachment.getAttachamentTitle();
        File  file=new File(filePath);
        String savePath = fileConfig.multipartConfigElement().getLocation() ;
        try {
            File newFile = new File(savePath);//转换之后文件生成的地址
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
//            String pdfFileName=fileName.substring(0,fileName.lastIndexOf("."));
//            String fileType=".pdf"; //pdf文件后缀
//            String newFileMix=savePath+pdfFileName+fileType;  //将这三个拼接起来,就是我们最后生成文件保存的完整访问路径了
//            //文件转化
//            converter.convert(file).to(new File(newFileMix)).execute();

            String newFileMix="";
            if(fileName.endsWith(".pdf")){
                String pdffileName=filePath.substring(filePath.lastIndexOf("/")+1);
                newFileMix=savePath+pdffileName;
            }else{
                String pdfFileName=fileName.substring(0,fileName.lastIndexOf("."));
                String fileType=".pdf"; //pdf文件后缀
                 newFileMix=savePath+pdfFileName+fileType;  //将这三个拼接起来,就是我们最后生成文件保存的完整访问路径了
                //文件转化
                converter.convert(file).to(new File(newFileMix)).execute();
            }

            //使用response,将pdf文件以流的方式发送的前端浏览器上
            ServletOutputStream outputStream = response.getOutputStream();
            InputStream in = new FileInputStream(new File(newFileMix));// 读取文件
            int i = IOUtils.copy(in, outputStream);   // copy流数据,i为字节数
            in.close();
            outputStream.close();
            System.out.println("流已关闭,可预览,该文件字节大小："+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


}
