package com.nantian.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.DocumentTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface DocumentTreeService extends IService<DocumentTree> {
    List<Map<String, Object>> getLeftTree();

    Map<String, Object> getDateil(String id);

    String getDocumentDesc(String documentClassParentId);


    boolean removeBydocumentClassLevel(String documentClassLevel);

}


