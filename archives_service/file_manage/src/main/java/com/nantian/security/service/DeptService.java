package com.nantian.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.security.Deptinfo;

import java.util.List;
import java.util.Map;

public interface DeptService  extends IService<Deptinfo> {
    IPage<Deptinfo> getSysDept(Page<Deptinfo> page, Deptinfo sysDeptinfo);

    public List<Map<String,Object>> getDeptUser();

}
