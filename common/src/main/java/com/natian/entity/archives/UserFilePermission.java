package com.natian.entity.archives;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.Release;
import com.natian.doamin.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 19:20
 * @Description:
 */
@Data
@TableName("d_user_file_permission")
public class UserFilePermission {
    @TableId(value="id",type = IdType.ASSIGN_UUID)
    @NotNull(message = "不可指定id",groups ={Update.class, Delete.class})
    @Null(message = "不需要指定id",groups = {Insert.class})
    private     String id;   //记录id

//    @NotNull(message = "用户名不能为空",groups = {Insert.class,Update.class})
//    @Null(message = "不需要指定用户名",groups = {Delete.class})
    @ApiModelProperty(value= "用户ID")
    private     String userId;  //用户ID

//    @NotNull(message = "档案标识不能为空",groups = {Insert.class,Update.class})
//    @Null(message = "不需要指定档案标识",groups = {Delete.class})
    @ApiModelProperty(value= "档案ID")
    @NotNull(message = "档案ID不能为空",groups = Insert.class)
    private     String fileId;   //档案ID

    @ApiModelProperty(value= "申请ID")
    @NotNull(message = "申请Id不能为空",groups = Insert.class)
    private  String permissionId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Null(message = "需要可传入审核时间",groups = {Insert.class,Update.class})
    @NotNull(message = "不需要可传入审核时间",groups = Release.class)
    @ApiModelProperty(value= "结束时间")
    private     String endTime;   //结束时间


/*
    @ApiModelProperty(value= "标题")
    private String title;
    @ApiModelProperty(value= "申请部门")
    private String deptNo;
    @ApiModelProperty(value= "内容")
    private String content;*/

    @Null(message = "不需传入传入审核标识",groups = {Insert.class,Update.class,Delete.class})
    @NotNull(message = "请指定发布标识，0或1",groups = Release.class)
    @ApiModelProperty(value= "审核状态")
    private     Integer auditStatus;   //审核状态

    @TableField(value="audit_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value= "审核时间")
    @Null(message = "不可传入创建时间",groups = {Insert.class,Update.class,Delete.class,Release.class})
    private     String auditTime;   //审核时间




    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Null(message="不可传入创建时间",groups = {Insert.class,Update.class,Delete.class})
    private String createTime;

    //修改时间
    @TableField(fill=FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Null(message="不可传入修改时间",groups = {Insert.class,Update.class})
    private String updateTime;



    //逻辑删除
    @TableLogic
    @TableField(fill =FieldFill.INSERT)
    @Null(message="不可传入删除标识",groups={Insert.class,Update.class,Delete.class})
    private Integer deleted;

    //版本
    @Version
    @TableField(fill=FieldFill.INSERT)
    @Null(message="不可传入版本号",groups = {Insert.class,Update.class})
    private Integer version;

}
