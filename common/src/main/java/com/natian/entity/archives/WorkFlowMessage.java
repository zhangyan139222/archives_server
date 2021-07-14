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
 * @date 2021/6/25 14:30
 * @Description:
 */
@Data
@TableName("file_workflow_message")
public class WorkFlowMessage {
    @TableId(value="id",type =IdType.ASSIGN_UUID)
    @Null(message = "不可指定id",groups = Insert.class)
    @NotNull(message = "需要指定id",groups = {Update.class, Delete.class,Release.class})
    @ApiModelProperty(value= "记录标识")
    private   String id;

    @ApiModelProperty(value= "流程Id")
    private  String folw_id;

    @ApiModelProperty(value= "用户名")
    private  String  user_id;

    @ApiModelProperty(value= "申请ID")
    private  String permission_id;

    @ApiModelProperty(value= "审核结果")
    private String process_message;

    @ApiModelProperty(value= "审核转态")
    private String process_status;
    //创建时间
    @TableField(value="create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Null(message = "不可传入创建时间",groups = {Insert.class, Update.class, Delete.class, Release.class})
    private String createTime;

    //更新时间
    @TableField(value="update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Null(message = "不可传入创建时间",groups = {Insert.class,Update.class,Delete.class,Release.class})
    private String  updateTime;

    //版本信息
//  @Version
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "不可传入版本号",groups = {Insert.class,Update.class,Delete.class,Release.class})
    private Integer  version;
    //逻辑删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "不可传入删除标识",groups = {Insert.class,Update.class,Delete.class,Release.class})
    private Integer deleted;

}
