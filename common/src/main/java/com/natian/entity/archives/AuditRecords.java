package com.natian.entity.archives;

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
 * @date 2021/7/6 14:51
 * @Description:
 */
@Data
@TableName("d_file_audit_records")
public class AuditRecords implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value="id",type = IdType.ASSIGN_UUID)
    @Null(message = "不可指定id",groups = Insert.class)
    @NotNull(message = "需要指定id",groups = {Update.class, Delete.class, Release.class})
    @ApiModelProperty(value= "记录标识")
    private String  id;


    @ApiModelProperty(value= "申请id")
    private String  requestId;
    @ApiModelProperty(value= "审核意见")
    private String  auditMind;
    @ApiModelProperty(value= "审核状态")
    private Integer  auditStatus;
    @ApiModelProperty(value= "审核时间")
    private String  auditTime;
}
