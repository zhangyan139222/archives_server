package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.CurrentInstrumentsMapper;
import com.nantian.archives.mapper.DocumentTreeMapper;
import com.nantian.archives.service.DocumentTreeService;
import com.natian.doamin.R;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.DocumentTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DocumentTreeServiceImpl extends ServiceImpl<DocumentTreeMapper, DocumentTree> implements DocumentTreeService {

    @Resource
    private DocumentTreeMapper documentTreeMapper;

    /**
     * 获取左侧的数据分类树
     * @return
     */

    @Override
    public List<Map<String,Object>> getLeftTree() {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByAsc("document_class_level");
        List<DocumentTree>  resultList=documentTreeMapper.selectList(queryWrapper);
        queryWrapper.eq("document_class_parent_id","10");
        List<DocumentTree>  treeList=documentTreeMapper.selectList(queryWrapper);
        List<Map<String,Object>>listMap=new ArrayList<>();
        //先找到所有的一级分类
        if (treeList != null && treeList.size() > 0) {
            for (DocumentTree documentTree : treeList) {
                Map<String, Object> treeMap = new HashMap<>();
                    treeMap.put("pkid", documentTree.getId());
                    treeMap.put("id", documentTree.getDocumentClassLevel());
                    treeMap.put("fatherId", documentTree.getDocumentClassParentId());
                    treeMap.put("label", documentTree.getDocumentClassName());
                    treeMap.put("children", new ArrayList<>());

                getChildList(documentTree.getDocumentClassLevel(), treeMap);
                listMap.add(treeMap);
            }
        }
        return listMap;
    }




    private void getChildList(String id,Map<String,Object> treeMap) {
        QueryWrapper<DocumentTree> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("document_class_parent_id",id);
        queryWrapper.orderByAsc("document_class_level");
        List<DocumentTree> treeList=documentTreeMapper.selectList(queryWrapper);
        List<Map<String, Object>> childList = new ArrayList<>();
        for (DocumentTree documentTree : treeList) {
            Map<String, Object> childMap = new HashMap<>();
                childMap.put("pkid", documentTree.getId());
                childMap.put("fatherId", documentTree.getDocumentClassParentId());
                childMap.put("id", documentTree.getDocumentClassLevel());
                childMap.put("label", documentTree.getDocumentClassName());
                //获取第三个节点数据
                getChildList(documentTree.getDocumentClassLevel(), childMap);
                childList.add(childMap);
               treeMap.put("children", childList);

        }
    }


    @Override
    public Map<String, Object> getDateil(String id) {
        return documentTreeMapper.getDateil(id) ;
    }

    @Override
    public String getDocumentDesc(String documentClassParentId) {
        return documentTreeMapper.getDocumentDesc(documentClassParentId);
    }

    @Override
    public boolean removeBydocumentClassLevel(String documentClassLevel) {
        return documentTreeMapper.removeBydocumentClassLevel(documentClassLevel);
    }

}
