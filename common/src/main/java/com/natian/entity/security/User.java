package com.natian.entity.security;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@TableName("sys_user")
@Data
//@Validated
public class User {
    //记录标识ID
    @TableId(value = "user_id" ,type = IdType.ASSIGN_UUID)
    @NotNull(message = "不可指定ID",groups = {Update.class, Delete.class})
    @Null(message = "需要指定ID",groups = {Insert.class})
    private String userId;

    //用户名称
    @NotNull(message = "用户名称不能为空",groups = {Insert.class,Update.class})
    @Null(message = "用户名称不需要指定",groups = {Delete.class})
    private String userName;
    //密码
    @NotNull(message = "密码不能为空",groups = {Insert.class,Update.class})
    @Null(message = "密码不需要指定",groups = {Delete.class})
    private String pwd;
    //邮箱

    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")
    private String email;
    //部门标识ID
    @NotNull(message = "部门标识不能为空",groups = {Insert.class,Update.class})
    @Null(message = "部门标识不需要指定",groups = {Delete.class})

    private String deptId;
    //性别
    private String sex;
    //联系方式
    private String mobile;

    @ApiModelProperty("用户真实姓名")
    private   String monicker;
    //状态
    private Integer status;

    /*//创建时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @Null(message = "不可传入时间",groups = {Insert.class,Update.class,Delete.class})
    private String createTime;
    //逻辑删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "不可传入删除标识",groups = {Insert.class,Update.class,Delete.class})
    private Integer deleted;
     //更新时间
    @TableField(fill=FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @Null(message = "不可传入时间",groups = {Insert.class,Update.class,Delete.class})
    private String updateTime;

    //版本
    //@Version
    @TableField(fill=FieldFill.INSERT)
    @Null(message = "不可传入版本号",groups = {Insert.class,Update.class,Delete.class})
    private Integer version;*/

    @TableField(exist = false)
   private   String deptName;

   @ApiModelProperty("用户级别")
  private    Integer  level;


}
