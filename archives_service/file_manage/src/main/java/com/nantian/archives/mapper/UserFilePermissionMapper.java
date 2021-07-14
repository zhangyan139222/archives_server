package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.UserFilePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/23 16:29
 * @Description:
 */
@Mapper
public interface UserFilePermissionMapper  extends BaseMapper<UserFilePermission> {
    Integer getFileIsPermission(@Param("fileId") String fileId);
}
