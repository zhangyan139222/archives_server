package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.natian.entity.archives.Archives;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ArchivesMapper extends BaseMapper<Archives> {

    IPage<Archives> getArchivesListByUser(IPage<Archives>iPage,
                                          @Param("userId") String userId,@Param("classNumber") String classNumber,
                                          @Param("title") String title, @Param("catalogNumber") String catalogNumber,
                                          @Param("responsiblePerson") String responsiblePerson );

      Archives getArchivesDetail(String id);

}
