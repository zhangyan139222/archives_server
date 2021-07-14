package com.nantian.archives.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nantian.archives.service.ArchivesService;
import com.natian.doamin.Insert;
import com.natian.doamin.R;
import com.natian.doamin.Update;
import com.natian.entity.archives.Archives;
import com.natian.entity.archives.CurrentInstrument;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/archives")
@Api(value="/archives",tags ={"文书档案"})
public class ArchivesController {
    @Resource
    private ArchivesService archivesService;

    @ApiOperation(value="查询所有数据",notes = "查询archives列表数据")
    @GetMapping("/getArchives/{page}/{size}")
    public R getArchives(@ApiParam(value="组合查询数据参数",required = false) Archives archives,
                                   @ApiParam(value="当前页码",required = false)   @PathVariable Integer page,
                                   @ApiParam(value="每页记录数",required = false)  @PathVariable Integer  size) {
        try {
            IPage<Archives> iPages = archivesService.getArchivesList(archives, page, size);
            return   R.ok("获取数据列表成功").data("iPages",iPages);
        } catch (Exception e) {
            return R.error("获取数据列表失败");
        }

    }

    @ApiOperation(value="根据用户查询所有数据",notes = "查询archives列表数据")
    @GetMapping("/getArchivesByUser")
    public R getArchivesListByUser( @ApiParam(value="当前页码",required = true)   Long page,
                                    @ApiParam(value="每页记录数",required = true) Long  size,
                                    @ApiParam(value="分类号",required = true) String classNumber,
                                    @ApiParam(value="用户名",required = false)  String userId,
                                    @ApiParam(value="题名",required = false)  String title,
                                    @ApiParam(value="目录号",required = false)  String catalogNumber,
                                    @ApiParam(value="责任者",required = false)  String responsiblePerson)
                         {
        try {
            IPage<Archives> iPages = archivesService.getArchivesListByUser(page, size,userId,classNumber,title,catalogNumber,responsiblePerson);
            return   R.ok("获取数据列表成功").data("iPages",iPages);
        } catch (Exception e) {
            return R.error("获取数据列表失败");
        }

    }

    /**
     * 数据新增
     * @param archives
     * @return
     */
    @ApiOperation(value="新增数据",notes="新增archives数据")
    @PostMapping("/saveArchives")
    public R  saveArchives(@RequestBody  @Validated({Insert.class}) Archives  archives){
        return archivesService.save(archives)? R.ok("数据新增成功"):R.error("数据新增失败");
    }

    /**
     * 数据更新
     * @param archives
     * @return
     */
    @ApiOperation(value="修改数据",notes = "更新archives数据")
    @PostMapping("/update")
    public R  updateArchives(@RequestBody  @Validated({Update.class}) Archives  archives){

        return archivesService.updateById(archives)? R.ok("数据更新成功"):R.error("数据更新失败");

    }


    /**
     * 获取数据详情
     * @param id
     * @return
     */

    @ApiOperation(value ="查看数据详情",notes = "查看archives数据详情")
    @GetMapping("/getDettail/{id}")
    public   R  getArchivesDetail(@ApiParam(value="需要查询数据的id",required = true)  @PathVariable String id){
        try {
            Archives   archives= archivesService.getById(id);
            return R.ok("获取数据详情成功").data("archives",archives);
        }catch (Exception  e){
            return R.error("获取数据详情失败");
        }

    }

    /**
     * 删除数据
     * @param id
     * @return
     */

    @ApiOperation(value ="删除数据",notes = "删除archives数据详情")
    @PostMapping("/deleteArchives/{id}")
    public   R  deleteArchives(@ApiParam(value="需要删除数据的id",required = true)  @PathVariable String id){
          return archivesService.removeById(id)? R.ok("删除数据成功"):R.error("删除数据失败");


    }


    @ApiOperation(value="审核数据",notes = "审核archives数据")
    @PostMapping("/updateExamine")
    public   R  updateExamine(@ApiParam(value = "需要修改的数据id", required = true) String id,
                              @ApiParam(value = "审核状态 0-1", required = true) Integer examine){

        try{
            Archives archives=new Archives();
            archives.setId(id);
            archives.setAuditStatus(examine);
//            currentInstrumentsService.updateExamine(dmStandardDocument);
            archivesService.updateById(archives);
            return R.ok("数据审核状态更新成功");
        }catch (Exception e){
            return R.error("数据审核状态更新失败");
        }


    }

}
