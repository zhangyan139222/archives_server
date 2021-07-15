package com.nantian.archives.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nantian.archives.service.UserFilePermissionService;
import com.nantian.archives.service.UserFileService;
import com.nantian.archives.service.WorkFlowService;
import com.nantian.security.service.UserService;
import com.natian.doamin.Insert;
import com.natian.doamin.R;
import com.natian.doamin.Update;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.UserFile;
import com.natian.entity.archives.UserFilePermission;
import com.natian.entity.archives.WorkFlow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/16 18:41
 * @Description:
 */
@RestController
@Api(tags ={"数据申请"})
@RequestMapping("userFile")
public class UserFileController {

    @Resource
    private UserFileService  userFileService;

    @ApiOperation(value = "根据用户查询所有数据")
    @GetMapping("/getAllListByUser")
    public R getUserFileListByUser(@ApiParam(value = "组合查询数据参数", required = false) UserFile userFile,
                                   @ApiParam(value = "当前页码", required = false) Integer page,
                                   @ApiParam(value = "每页记录数", required = false) Integer size,
                                   @ApiParam(value = "用户名", required = true) String userId) {
        try {

            Map<String, Object> userFileListByUser = userFileService.getUserFileListByUser(userId, userFile, page, size);
            return R.ok("获取数据列表成功").data(userFileListByUser);
        } catch (Exception e) {
            return R.error("获取数据列表失败");
        }

    }

    /**
     * 数据新增
     *
     * @param userFile
     * @return
     */
    @ApiOperation(value = "新增档案查阅申请")
    @PostMapping("/saveUserFile")
    public R saveUserFile(@RequestBody @Validated({Insert.class}) UserFile userFile) {
        return userFileService.saveUserFile(userFile) ? R.ok("档案查阅申请新增成功") : R.error("档案查阅申请新增失败");

    }


    /**
     * 数据更新
     *
     * @param userFile
     * @return
     */
    @ApiOperation(value = "修改档案查阅申请")
    @PostMapping("/update")
    public R updateUserFile(@RequestBody @Validated({Update.class}) UserFile userFile) {

        return userFileService.updateUserFile(userFile) ? R.ok("数据更新成功") : R.error("数据更新失败");

    }

    /**
     * 数据审核
     *
     * @param id
     * @param auditStatus
     * @return
     */
    @ApiOperation(value = "档案查阅申请审核")
    @PostMapping("/examineUserFile")
    public R examineUserFile(@ApiParam(value = "id", required = true) String id,
                             @ApiParam(value = "审核状态", required = true) Integer auditStatus,
                             @ApiParam(value = "驳回理由", required = false) String auditMind,
                             @ApiParam(value = "用户ID", required = false) String userId) {


        return userFileService.examineUserFile(id, auditStatus, auditMind, userId) ? R.ok("档案查阅申请成功") : R.error("档案查阅申请失败");

    }

    @ApiOperation(value = "档案查阅申请删除")
    @PostMapping("/deleteUserFile/{id}")
    public R deleteUserFile(@ApiParam(value = "档案查阅申请ID", required = true) @PathVariable String id) {
        return userFileService.removeById(id) ? R.ok("档案查阅申请删除成功") : R.error("档案查阅申请删除失败");
    }


}
