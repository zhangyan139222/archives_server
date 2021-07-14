package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.ArchivesMapper;
import com.nantian.archives.service.ArchivesService;
import com.natian.entity.archives.Archives;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ArchivesServiceImpl extends ServiceImpl<ArchivesMapper, Archives> implements ArchivesService {
    @Resource
    private ArchivesMapper archivesMapper;

    /**
     * 获取所有的数据（组合条件+数据分页）
     * @param archives
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Archives> getArchivesList(Archives archives, Integer page, Integer size) {
        Page<Archives> pages=new Page<>(page,size);
        QueryWrapper<Archives> queryWrapper=new QueryWrapper<>();
        IPage<Archives> archivesIPage = archivesMapper.selectPage(pages, null);
        return archivesIPage;
    }

    @Override
    public IPage<Archives> getArchivesListByUser(Long page, Long size,String userId,String classNumber,
                                                 String title,String catalogNumber,String responsiblePerson ){
        IPage<Archives> iPage = null;
        Page<Archives> pages=new Page<>(page,size);
        if (StringUtils.isEmpty(userId) || userId.equals("admin")) {    //管理员获取所有的数据
            QueryWrapper<Archives> queryWrapper = new QueryWrapper<>();
            if(StringUtils.isNotEmpty(title)){
                queryWrapper.like("TITLE",title);
            }
            if(StringUtils.isNotEmpty(responsiblePerson)){
                queryWrapper.like("ZRZ",responsiblePerson);
            }
            if(StringUtils.isNotEmpty(catalogNumber)){
                queryWrapper.like("F15",catalogNumber);
            }
            if(StringUtils.isNotEmpty(classNumber)){
                queryWrapper.likeRight("FLH",classNumber);
            }
            iPage = archivesMapper.selectPage(pages, queryWrapper);
        } else {     //其他普通用户获取自己申请通过的数据及自己上传审核通过的数据
            iPage=archivesMapper.getArchivesListByUser(pages,userId,classNumber,title,catalogNumber,responsiblePerson);

        }
        return iPage;
    }

    @Override
    public Archives getArchivesDetail(String id) {
        return null;
    }
}
