package com.natian.entity.archives;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("d_file_operation_logging")
public class FileOperationLogging implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value= "操作Id")
    @TableId(value="oper_id",type = IdType.ASSIGN_UUID)
    @Null(message = "不可指定id",groups = Insert.class)
    @NotNull(message = "需要指定id",groups = {Update.class, Delete.class, Release.class})
    private   String operId;

    @ApiModelProperty(value= "档案Id")
    private    String fileId;

    @ApiModelProperty(value= "用户Id")
    private   String userId;

    @ApiModelProperty(value= "用户真实姓名")
    @TableField(exist = false)
    private   String monicker;

    @ApiModelProperty(value= "请求类型")
    private   Integer operType;

    @ApiModelProperty(value= "操作名称")
    private   String operName;



    @ApiModelProperty(value= "请求结束时间")
    private   String finishTime;



    @ApiModelProperty(value= "操作描述")
    private   String operDesc;




}
