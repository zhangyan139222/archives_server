package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.natian.entity.archives.FileAttachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/7/7 14:17
 * @Description:
 */
@Mapper
public interface FileAttachmentMapper   extends BaseMapper<FileAttachment> {

    @Select("select *   from d_file_attachment  dfa  where dfa.archives_id=#{archivesId}")
    List<FileAttachment>  getFileAttachmentList(@Param("archivesId") String archivesId);
}
