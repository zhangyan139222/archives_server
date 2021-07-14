package com.natian.entity.security;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/17 10:01
 * @Description:
 */
@Data
@TableName("sys_menu")
public class Menu {
    //菜单ID
    private   String menu_id;
    //菜单编码
    private   String menu_code;
    //父级菜单编码
    private   String menu_parent_code;
    //菜单名称
    private   String menu_name;
    //菜单地址
    private   String menu_url;
    //菜单地址
    private   String menu_icon;
    //是否可见
    private   String isVisible;
    //菜单排序
    private   String menu_sort;
    //菜单类型
    private   String menu_type;
}
