package com.nantian.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.security.mapper.DeptMapper;
import com.nantian.security.mapper.UserMapper;
import com.nantian.security.service.DeptService;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.DocumentTree;
import com.natian.entity.security.Deptinfo;
import com.natian.entity.security.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl   extends ServiceImpl<DeptMapper, Deptinfo>  implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    @Resource
    private UserMapper  userMapper;

    public IPage<Deptinfo> getSysDept(Page<Deptinfo> page, Deptinfo sysDeptinfo) {
        QueryWrapper<Deptinfo> queryWrapper=new QueryWrapper<>();
        System.out.println(baseMapper.selectList(queryWrapper));
        IPage<Deptinfo>  iPage =deptMapper.selectPage(page,null);
        return iPage;
    }




    /**
     * 获取左侧的数据分类树
     * @return
     */

    @Override
    public List<Map<String,Object>> getDeptUser() {
        QueryWrapper<Deptinfo> queryWrapper=new QueryWrapper();
        queryWrapper.orderByAsc("dept_id");
        List<Deptinfo>  resultList=deptMapper.selectList(queryWrapper);
        List<Map<String,Object>>listMap=new ArrayList<>();
        //先找到所有的一级分类
        if (resultList != null && resultList.size() > 0) {
            for (Deptinfo deptinfo : resultList) {
                Map<String, Object> treeMap = new HashMap<>();
                treeMap.put("value", deptinfo.getDeptId());
                treeMap.put("label", deptinfo.getDeptName());
                treeMap.put("children", new ArrayList<>());

                getChildList(deptinfo.getDeptId(), treeMap);
                listMap.add(treeMap);
            }
        }
        return listMap;
    }

    private void getChildList(String id,Map<String,Object> treeMap) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("dept_id",id);
        List<User> treeList=userMapper.selectList(queryWrapper);
        List<Map<String, Object>> childList = new ArrayList<>();
        for (User user : treeList) {
            Map<String, Object> childMap = new HashMap<>();
            childMap.put("value", user.getUserId());
            childMap.put("label", user.getMonicker());
            childList.add(childMap);
            treeMap.put("children", childList);

        }
    }

}