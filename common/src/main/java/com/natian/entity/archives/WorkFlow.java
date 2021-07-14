package com.natian.entity.archives;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.Release;
import com.natian.doamin.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/25 14:05
 * @Description:
 */

@Data
@TableName("file_workflow")
public class WorkFlow {

    @TableId(value="folw_id",type = IdType.ASSIGN_UUID)
    @Null(message = "不可指定id",groups = Insert.class)
    @NotNull(message = "需要指定id",groups = {Update.class, Delete.class, Release.class})
    private  String  folwId;

    @TableField(value="permission_id")
    @NotNull(message = "申请ID不能为空",groups = Insert.class)
    private  String permissionId;

    @TableField(value="user_id")
    @NotNull(message = "用户名不能为空",groups = Insert.class)
    private   String userId;

    @TableField(value="process_status")
    private Integer processStatus;

    @TableField(value="node_id")
    private   Integer nodeId;

    @TableField(value="node_status")
    private Integer  nodeStatus;

    @TableField(value="permission_message")
    private String permissionMessage;

    @TableField(value="dept_id")
    private   String  deptId;
    //创建时间
    @TableField(value="create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Null(message = "不可传入创建时间",groups = {Insert.class,Update.class,Delete.class})
    private String createTime;

    //更新时间
    @TableField(value="update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Null(message = "不可传入创建时间",groups = {Insert.class,Update.class,Delete.class})
    private String  updateTime;

    //版本信息
//  @Version
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "不可传入版本号",groups = {Insert.class,Update.class,Delete.class})
    private Integer  version;
    //逻辑删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "不可传入删除标识",groups = {Insert.class,Update.class,Delete.class})
    private Integer deleted;
}
