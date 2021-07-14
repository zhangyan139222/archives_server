package com.natian.entity.archives;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@TableName("d_document_tree")
public class DocumentTree implements Serializable {
    private static final long serialVersionUID = 1L;
    //生成ID的方式
    @TableId(type= IdType.ASSIGN_UUID)
    @Null(message="不可指定ID",groups= Insert.class)
    @NotNull(message="需指定ID",groups ={Update.class, Delete.class})
    private  String  id;

    //数据档案分类名称
    @Size(min=1,max=100,message="数据标准分类名称长度需要在1-100之间",groups = {Insert.class,Update.class})
    @NotNull(message="名称不能为空",groups = {Insert.class,Update.class})
    @Null(message="名称不需要指定",groups = {Delete.class})
    private String documentClassName;

    //数据档案分类当前等级
    @Null(message="菜单分类等级不需要指定",groups = {Insert.class,Update.class})
    private String documentClassLevel;

    //数据档案分类父级ID
//    @NotNull(message="父级不能为空",groups = {Insert.class})
    @Null(message="父级不需要指定",groups = {Update.class})
    private String documentClassParentId;


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

    //备注
//    @Size(max=100,message = "备注长度不可大于100",groups={Insert.class,Update.class})
//    @Null(message="备注不需要指定",groups={Insert.class,Update.class})
    private String documentClassRemarks;

    //版本
    @Version
    @TableField(fill=FieldFill.INSERT)
    @Null(message="不可传入版本号",groups = {Insert.class,Update.class})
    private Integer version;

}
