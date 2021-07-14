package com.natian.entity.security;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.util.List;

@TableName("sys_function")
@Data
public class Permission {
    //记录标识ID
    private  String  id;
    //权限标识
    private  String  functionId;
    //权限名称
    private  String  functionName;
    //菜单地址
    private  String  functionUrl;
    //父级ID
    private  Integer  parentId;
    //菜单图标ID
    private  String  icond;
    //菜单类型
    private  Integer functionType;
    //菜单排序
    private  Integer functionOrder ;

    @TableField(exist = false)
    private String userId;
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String pwd;
    @TableField(exist = false)
    private String roleId;
    @TableField(exist = false)
    private List<Permission> child;






}
