package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.DocumentTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface DocumentTreeMapper extends BaseMapper<DocumentTree> {


    @Select("select document_class_name  documentClassName, document_class_remarks  AS documentClassRemarks from  d_document_tree    WHERE   id=#{id}")
    Map<String,Object> getDateil(@Param("id") String id);

    @Select("select document_class_level  from  d_document_tree  where  document_class_parent_id=#{documentClassParentId}  order by  document_class_level desc limit 1")
    String getDocumentDesc(@Param("documentClassParentId") String documentClassParentId);


    @Update("update d_document_tree    set deleted=1       where document_class_level=#{documentClassLevel}  and  deleted=0")
      boolean removeBydocumentClassLevel(@Param("documentClassLevel") String documentClassLevel);

}
