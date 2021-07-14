package com.natian.entity.security;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@TableName("sys_deptinfo")
public class Deptinfo {
    //部门ID
    @TableId(type = IdType.ASSIGN_UUID)
    @NotNull(message = "不可指定ID",groups = {Update.class, Insert.class,Delete.class})
    private String deptId;
    //部门名称
    @NotNull(message = "部门名不能为空",groups = {Insert.class,Update.class})
    @Null(message = "部门名不需要指定",groups = {Delete.class})
    private String deptName;
    //上级单位Id
    @NotNull(message = "上级部门名不能为空",groups = {Insert.class,Update.class})
    @Null(message = "上级名不需要指定",groups = {Delete.class})
    private String parentDeptId;
    //组织机构编码
    @NotNull(message = "组织机构编码不能为空",groups = {Insert.class,Update.class})
    @Null(message = "组织机构编码不需要指定",groups = {Delete.class})
    private String orgCode;
    //组织机构类型
    private String orgType;
    //电话
    private String mobile;
    //传真
    private String fax;
    //地址
    private String address;
    //描述
    private String description;
    /*//创建时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @Null(message = "不可传入时间",groups = {Insert.class,Update.class,Delete.class})
    private String createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @Null(message = "不可传入时间",groups = {Insert.class,Update.class,Delete.class})
    private String updateTime;
    //0表示未删除1，1表示删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "不可传入删除标识",groups = {Insert.class, Update.class, Delete.class})
    private Integer deleted;
    //版本号
    //@Version
    @TableField(fill=FieldFill.INSERT)
    @Null(message = "不可传入版本号",groups = {Insert.class,Update.class,Delete.class})
    private Integer version;*/


}
