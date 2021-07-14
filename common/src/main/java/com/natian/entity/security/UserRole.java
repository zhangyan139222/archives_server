package com.natian.entity.security;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_user_role")
@Data
public class UserRole {
    //记录标识ID
    private String id;
    //角色标识
    private String roleId;
    //用户标识
    private String userId;
   /* //创建时间
    private String createTime;
    //0表示未删除1，1表示删除
    private Integer deleted;
    //更新时间
    private String updateTime;
    //版本号
    private Integer version;*/


}
