package com.natian.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.Release;
import com.natian.doamin.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/27 14:34
 * @Description:
 */
@Data
@TableName("sys_operation_logging")
public class OperationLogging implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value= "操作Id")
    @TableId(value="oper_id",type = IdType.ASSIGN_UUID)
    @Null(message = "不可指定id",groups = Insert.class)
    @NotNull(message = "需要指定id",groups = {Update.class, Delete.class, Release.class})
    private   String operId;

    @ApiModelProperty(value= "用户Id")
    private   String userId;

    @ApiModelProperty(value= "用户名")
    private   String userName;

    @ApiModelProperty(value= "请求类型")
    private   String operType;

    @ApiModelProperty(value= "操作名称")
    private   String operName;

    @ApiModelProperty(value= "请求名称")
    private   String operModule;

    @ApiModelProperty(value= "请求描述")
    private   String requestDes;

    @ApiModelProperty(value= "请求描述")
    private   String methodName;

    @ApiModelProperty(value= "请求ip")
    private   String operIp;

    @ApiModelProperty(value= "请求地址")
    private   String operUrl;

    @ApiModelProperty(value= "请求参数")
    private   String requestParam;

    @ApiModelProperty(value= "返回值")
    private   String reponseData;

    @ApiModelProperty(value= "请求开始时间")
    private   String startTime;

    @ApiModelProperty(value= "请求结束时间")
    private   String finishTime;

    @ApiModelProperty(value= "接口返回时间")
    private   String retrunTime;

    @ApiModelProperty(value= "操作描述")
    private   String operDesc;




}
