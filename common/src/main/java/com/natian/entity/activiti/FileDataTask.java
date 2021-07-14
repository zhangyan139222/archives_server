package com.natian.entity.activiti;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("file_workflow")
public class FileDataTask {
    //表单ID
    @TableId(type = IdType.ASSIGN_ID)
    private String fromId;
    //activiti流程实例ID
    private String processInstanceId;
    //用户ID
    private String userId;
    //开始时间
    private String beginDate;
    //结束时间
    private String endDate;
    //流程状态0 申请，1审核中，2已完成
    private String processStatus;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
    //档案题名
    private String title;
}
