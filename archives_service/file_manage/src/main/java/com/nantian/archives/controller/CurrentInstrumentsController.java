package com.nantian.archives.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nantian.archives.service.CurrentInstrumentsService;
import com.nantian.archives.service.UserFileService;
import com.natian.doamin.Insert;
import com.natian.doamin.R;
import com.natian.doamin.Release;
import com.natian.doamin.Update;
import com.natian.entity.archives.CurrentInstrument;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("CurrentInstruments")
@Api(tags ={"现行文书"})
public class CurrentInstrumentsController {

    @Resource
    private CurrentInstrumentsService currentInstrumentsService;
    @Resource
    private UserFileService  userFileService;

    @ApiOperation(value = "查询已归档的数据")
    @GetMapping("/getAllList")
    public R getCurrentInstruments(@ApiParam(value = "当前页码", required = true) Integer page,
                                   @ApiParam(value = "每页记录数", required = true) Integer size,
                                   @ApiParam(value = "分类号", required = true) String documentClassLevel) {
        try {
            IPage<CurrentInstrument> iPages = currentInstrumentsService.getCurrentInstrumentList(page, size, documentClassLevel);
            return R.ok("获取数据列表成功").data("iPages", iPages);
        } catch (Exception e) {
            return R.error("获取数据列表失败");
        }

    }



@ApiOperation(value="根据用户查询所有数据",notes = "查询CurrentInstruments列表数据")
@GetMapping("/getAllListByUser")
public R getCurrentInstrumentsListByUser( @ApiParam(value="当前页码",required = false)   Integer page,
                                          @ApiParam(value="每页记录数",required = false)   Integer  size,
                                          @ApiParam(value="分类号",required = false) String documentClassLevel,
                                          @ApiParam(value="用户ID",required = false)  String userId,
                                          @ApiParam(value="审核状态",required = false)  String auditStatus) {
    try {
        Map<String, Object> map = currentInstrumentsService.getCurrentInstrumentListByUser(page, size, userId, documentClassLevel, auditStatus);
        return   R.ok("获取数据列表成功").data(map);
    } catch (Exception e) {
        return R.error("获取数据列表失败");
    }

}

    /**
     * 数据新增
     *
     * @param currentInstrument
     * @return
     */
    @ApiOperation(value = "新增数据", notes = "新增CurrentInstruments数据")
    @PostMapping("/saveCurrentInstruments")
    @Transactional
    public R saveCurrentInstruments(@RequestBody @Validated({Insert.class}) CurrentInstrument currentInstrument) {

        return currentInstrumentsService.saveCurrentInstruments(currentInstrument) ? R.ok("数据新增成功") : R.ok("数据新增失败");

    }

    /**
     * 数据更新
     *
     * @param currentInstrument
     * @return
     */
    @ApiOperation(value = "修改数据", notes = "更新CurrentInstruments数据")
    @PostMapping("/update")
    @Transactional
    public R updateCurrentInstruments(@RequestBody @Validated({Update.class}) CurrentInstrument currentInstrument) {
        return currentInstrumentsService.updateCurrentInstruments(currentInstrument) ? R.ok("数据更新成功") : R.ok("数据更新失败");
    }


    /**
     * 获取数据详情
     * @param id
     * @return
     */

    @ApiOperation(value = "查看数据详情", notes = "查看CurrentInstruments数据详情")
    @GetMapping("/getDetail/{id}/{permissionId}")
    public R getCurrentInstrumentsDetail(@ApiParam(value = "需要查询数据的ID", required = true) @PathVariable String id,
                                         @ApiParam(value = "申请的ID", required = true) @PathVariable String permissionId) {
        try {
            CurrentInstrument currentInstrument = currentInstrumentsService.getCurrentInstrumentsDetail(id, permissionId);
            return R.ok("获取数据详情成功").data("currentInstrument", currentInstrument);
        } catch (Exception e) {
            return R.error("获取数据详情失败");
        }

    }


    @ApiOperation(value="获取左侧树形结果集")
    @GetMapping("/getLeftTree")
    public  R  getLeftTree(){
        try{
            List<Map<String, Object>> leftTree = currentInstrumentsService.getLeftTree();
            return R.ok("获取树形列表成功").data("leftTree",leftTree);
        }catch (Exception  e){
            return R.ok("获取树形列失败");
        }

    }


    /**
     * 删除数据
     * @param id
     * @return
     */

    @ApiOperation(value ="删除数据",notes = "删除currentInstrument数据详情")
    @PostMapping("/delete/{id}/{userId}")
    public   R  deleteCurrentInstruments(@ApiParam(value="需要删除数据的id",required = true)  @PathVariable String id,
                                         @ApiParam(value="用户ID",required = true)  @PathVariable String userId ){
        return currentInstrumentsService.deleteCurrentInstruments(id,userId)? R.ok("删除数据成功"):R.error("删除数据失败");
    }


    /**
     * 审核操作
     */
    @ApiOperation(value = "审核数据", notes = "审核currentInstrument数据")
    @PostMapping("/updateExamine")
    @Transactional
    public R updateExamine(@ApiParam(value = "id", required = true) @Validated({Release.class}) String id,
                           @ApiParam(value = "审核状态", required = true) Integer auditStatus,
                           @ApiParam(value = "驳回理由", required = false) String auditMind,
                           @ApiParam(value = "用户ID", required = true) String userId) {

        return currentInstrumentsService.updateExamine(id, auditStatus, auditMind, userId) ?
                R.ok("数据审核状态更新成功") : R.error("数据审核状态更新失败");


    }


    /**
     * 档案归档
     */
    @ApiOperation(value = "档案归档")
    @PostMapping("/updateIsFile")
    @Transactional
    public R updateIsFile(@ApiParam(value = "id", required = true) String id,
                          @ApiParam(value = "档案分类号", required = true) String documentClassLevel,
                          @ApiParam(value = "用户ID", required = true) String userId) {
        return currentInstrumentsService.updateIsFile(id, documentClassLevel, userId) ?
                R.ok("数据归档成功") : R.error("数据归档失败");


    }


    @ApiOperation(value="获取已授权数据")
    @GetMapping("/getPermissionFile")
    public  R    getPermissionFile(@ApiParam(value = "申请ID",required = true)String id,
                                   @ApiParam(value="当前页码",required = true)Long page,
                                   @ApiParam(value="每页记录数",required = true) Long  size,
                                   @ApiParam(value="用户ID",required = true) String  userId){
        try{
            IPage<CurrentInstrument> permissionFile = currentInstrumentsService.getPermissionFile(page,size, userId, id);
            return   R.ok("获取已授权数据列表成功").data("permissionFile",permissionFile);
        }catch (Exception e){
            return R.error("获取已授权数据列表失败");
        }

    }


    @ApiOperation(value="查询今天所有数据")
    @GetMapping("/getAllListByDay/{userId}")
    public  R  getAllListByDay(@ApiParam(value = "id",required = true)  @PathVariable String userId){
        try {
            Map<String, Object> allListByDay = currentInstrumentsService.getAllListByDay(userId);
            return R.ok("成功获取今天的所有数据").data(allListByDay);
        }catch (Exception  e){
            e.printStackTrace();
            return R.ok("获取今天的所有数据失败");
        }
    }


    @ApiOperation(value = "查询所有的数据(归档、查档)")
    @GetMapping("/getList")
    public R getAllList(@ApiParam(value = "id", required = true) String userId,
                        @ApiParam(value = "当前页码", required = true) Long page,
                        @ApiParam(value = "每页记录数", required = true) Long size,
                        @ApiParam(value = "申请人", required = false) String applyUser,
                        @ApiParam(value = "申请类型", required = true) Integer applyType) {
        try {
            Map<String, Object> allListByDay = currentInstrumentsService.getAllList(userId, page, size, applyUser, applyType);
            return R.ok("成功获取今天的所有数据").data(allListByDay);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("获取今天的所有数据失败");
        }
    }

    @ApiOperation(value = "统计分析")
    @GetMapping("/getAllTotal")
    public R getAllTotal() {
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> fileMap = new ArrayList<>();
            List<Map<String, Object>> lookMap = new ArrayList<>();
            Map<String, Object> auditMap = currentInstrumentsService.getAllTotalByAudit();
            Map<String, Object> auditMonthMap = currentInstrumentsService.getAllTotalByAuditByMonth();
            Map<String, Object> auditMap1 = userFileService.getAllTotalByAudit();
            Map<String, Object> auditMonthMap1 = userFileService.getAllTotalByAuditByMonth();
            fileMap.add(auditMap);
//            fileMap.add(auditMonthMap);
//            lookMap.add(auditMonthMap1);
            lookMap.add(auditMap1);
            map.put("file", fileMap);
            map.put("look", lookMap);
            return R.ok("成功获取统计结果").data(map);
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("获取统计结果失败");
        }

    }


    @ApiOperation(value = "修改归档分类号")
    @GetMapping("/updateClassNameber")
    public R updateClassNameber(@ApiParam(value = "档案ID", required = true) String id,
                                @ApiParam(value = "档案分类ID", required = true) String documentClassLevel) {
        CurrentInstrument currentInstrument = new CurrentInstrument();
        currentInstrument.setId(id);
        currentInstrument.setDocumentClassLevel(documentClassLevel);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentInstrument.setUpdateTime(simpleDateFormat.format(new Date()));
        return currentInstrumentsService.updateById(currentInstrument) ? R.ok("归档数据修改成功") : R.ok("归档数据修改失败");

    }


}