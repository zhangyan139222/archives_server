package com.nantian.archives.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nantian.archives.service.CurrentInstrumentsService;
import com.nantian.archives.service.DocumentTreeService;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.R;
import com.natian.doamin.Update;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.DocumentTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("documentTree")
@Api(tags = {"左侧树管理"})
public class DocumentTreeController {

    @Resource
    private DocumentTreeService documentTreeService;
    @Resource
    private CurrentInstrumentsService currentInstrumentsService;

    @ApiOperation(value = "档案分类新增")
    @PostMapping("/saveDocumentTree")
    public R saveDocumentTree(@RequestBody @Validated({Insert.class}) DocumentTree documentTree) {
        QueryWrapper<CurrentInstrument> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("document_class_level", documentTree.getDocumentClassParentId());
        List<CurrentInstrument> list = currentInstrumentsService.list(queryWrapper);  //分类下是否有文档
        String documentDesc = documentTreeService.getDocumentDesc(documentTree.getDocumentClassParentId());
        int childSize = 1;
        if (StringUtils.isNotEmpty(documentDesc)) {
            childSize = Integer.parseInt(documentDesc.substring(documentDesc.length() - 2, documentDesc.length())) + 1;
        }
        DecimalFormat ch = new DecimalFormat("00");
        String child = ch.format(childSize);
        if (documentTree.getDocumentClassParentId() == null) {   //第一级目录
            documentTree.setDocumentClassParentId("10");
            documentTree.setDocumentClassLevel(documentTree.getDocumentClassParentId() + child);
        } else {
            if (list.size() > 0) {     //该节点下有数据
                return R.error("当前分类下已有数据，不可继续添加分类");
            } else {
                documentTree.setDocumentClassLevel(documentTree.getDocumentClassParentId() + child);

            }
        }
        return documentTreeService.save(documentTree) ? R.ok("数据新增成功") : R.error("数据新增失败");
    }


    /**
     * 更新数据
     *
     * @param documentTree
     * @return
     */
    @ApiOperation(value = "档案分类更新")
    @PostMapping("/updateDocumentTree")
    public R updateDocumentTree(@RequestBody @Validated({Update.class}) DocumentTree documentTree) {
        DocumentTree byId = documentTreeService.getById(documentTree.getId());
        documentTree.setVersion(byId.getVersion());
        return documentTreeService.updateById(documentTree) ? R.ok("数据更新成功") : R.error("数据更新失败");
    }


    @ApiOperation(value = "档案分类删除")
    @PostMapping("/deleteDocumentTree/{documentClassLevel}")
    public R deleteDocumentTree(@PathVariable(value = "documentClassLevel")
                                @ApiParam(value = "需要删除数据分类号", required = true) String documentClassLevel) {
        QueryWrapper<DocumentTree> documentTreeWrapper = new QueryWrapper<>();
        documentTreeWrapper.eq("document_class_parent_id", documentClassLevel);
        List<DocumentTree> documentlist = documentTreeService.list(documentTreeWrapper);
        if (documentlist.size() > 0) {
            return R.error("请先删除子目录,再重试！");
        }
        QueryWrapper<CurrentInstrument> currentInstrumentQueryWrapper = new QueryWrapper<>();
        currentInstrumentQueryWrapper.eq("document_class_level", documentClassLevel);
        if (currentInstrumentsService.list(currentInstrumentQueryWrapper).size() > 0) {
            return R.error("请先删除分类下的数据,再重试！");
        }
        if (documentClassLevel.length() <= 8) {
            return R.error("该目录不能被删除！");
        }
        return documentTreeService.removeBydocumentClassLevel(documentClassLevel) ? R.ok("数据删除成功") : R.error("数据删除失败");
    }


    @ApiOperation(value = "档案分类查看")
    @GetMapping("/selectDetail/{id}")
    public R selectDocumentDetail(@PathVariable @ApiParam(value = "ID", required = true) String id) {
        try {
            Map<String, Object> dateil = documentTreeService.getDateil(id);
            return R.ok("获取列表详情成功").data("dateil", dateil);
        } catch (Exception e) {
            return R.ok("获取列表详情失败");
        }
    }


    @ApiOperation(value = "获取左侧树形结果集")
    @GetMapping("/getLeftTree")
    public R getLeftTree() {
        try {
            List<Map<String, Object>> leftTree = documentTreeService.getLeftTree();
            return R.ok("获取树形列表成功").data("leftTree", leftTree);
        } catch (Exception e) {
            return R.ok("获取树形列失败");
        }

    }

}