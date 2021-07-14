package com.natian.entity.security;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@TableName("sys_role")
@Data
public class Role {
    //记录标识ID
    @TableId(type=IdType.ASSIGN_UUID)
    @NotNull(message = "不可指定ID",groups = {Update.class, Delete.class})
    @Null(message = "需要指定ID",groups = {Insert.class})
    private String  id;
    //角色英文标识
    @NotNull(message = "角色不能为空",groups = {Insert.class,Update.class})
    @Null(message = "角色不需要指定",groups = {Delete.class})
    private String  roleId;
    //角色中文名称
    @NotNull(message = "角色名称不能为空",groups = {Insert.class,Update.class})
    @Null(message = "角色名称不需要指定",groups = {Delete.class})
    private String  roleName;
   /* //创建时间
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @Null(message = "不可传入时间",groups = {Insert.class,Update.class,Delete.class})
    private String  createTime;
    //0表示未删除1，1表示删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "不可传入删除标识",groups = {Insert.class,Update.class,Delete.class})
    private Integer  deleted;
    //更新时间
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @Null(message = "不可传入时间",groups = {Insert.class,Update.class,Delete.class})
    private String  updateTime;
    //版本号
    //@Version
    @TableField(fill=FieldFill.INSERT)
    @Null(message = "不可传入版本号",groups = {Insert.class,Update.class,Delete.class})
    private Integer  version;*/


}
