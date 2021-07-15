package com.nantian.archives.controller;

import com.nantian.archives.service.FileOperationLoggingService;
import com.natian.doamin.R;
import com.natian.entity.archives.FileOperationLogging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/6 15:43
 * @Description:
 */
@RestController
@RequestMapping("operationLogging")
@Api(tags ={"操作日志"})
public class FileOperationLoggingController {

    @Resource
    private FileOperationLoggingService   fileOperationLoggingService;

    @ApiOperation(value = "获取数据对应的操作日志")
    @GetMapping("/getOperationList/{fileId}")
    public R getOperationList(@PathVariable @ApiParam(value = "档案ID", required = true) String fileId) {
        try {
            List<FileOperationLogging> operationList = fileOperationLoggingService.getOperationList(fileId);
            return R.ok("获取操作日志成功").data("operationList", operationList);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("获取操作日志失败");
        }

    }
}
