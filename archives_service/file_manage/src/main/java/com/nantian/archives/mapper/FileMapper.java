package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.natian.entity.archives.CurrentInstrument;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper  extends BaseMapper<CurrentInstrument> {
}
