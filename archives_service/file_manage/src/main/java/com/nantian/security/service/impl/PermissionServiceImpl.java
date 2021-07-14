package com.nantian.security.service.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.security.mapper.PermissionMapper;
import com.nantian.security.service.PermissionService;
import com.natian.entity.security.Permission;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 10:30
 * @Description:
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>  implements PermissionService {
   @Resource
    private   PermissionMapper  permissionMapper;

    @Override
    public List<Permission> PermissionListByUser(String userId) {
        List<Permission>list=permissionMapper.PermissionListByUser(userId);
        List<Permission> menus = list.stream().filter(m -> m.getParentId().equals(1))
                .collect(Collectors.toList());
        //支持多级菜单
        List<Permission> firstLevel = list.stream().filter(p -> p.getParentId().equals(0)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(m -> {
            setChild(m, menus);
        });

        return firstLevel;

//        return permissionMapper.PermissionListByUser(userId);
    }


    /**
     * 设置子元素
     * @param permission
     * @param menus
     */
    private void setChild(Permission permission, List<Permission> menus) {
        List<Permission> child = menus.parallelStream().filter(a -> a.getParentId().equals(permission.getFunctionType()))
                .collect(Collectors.toList());
        permission.setChild(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, menus);
            });
        }
    }
}
