package com.nantian.archives.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.Archives;
import com.natian.entity.archives.CurrentInstrument;

import java.util.List;


public interface ArchivesService extends IService<Archives> {
    public IPage<Archives> getArchivesList(Archives archives, Integer page, Integer size);

    public IPage<Archives> getArchivesListByUser(Long page, Long size,String userId,String classNumber,
                                                 String title,String catalogNumber,String responsiblePerson );

    Archives getArchivesDetail(String id);
}
