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
 * @date 2021/7/6 18:46
 * @Description:
 */
@Data
@TableName("d_file_attachment")
public class FileAttachment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value="id",type = IdType.ASSIGN_UUID)
    @Null(message = "不可指定id",groups = Insert.class)
    @NotNull(message = "需要指定id",groups = {Update.class, Delete.class, Release.class})
    @ApiModelProperty(value= "记录标识")
    private String id;


    @ApiModelProperty(value= "档案ID")
    @NotNull(message = "需要指定档案ID",groups = {Insert.class})
    private String archivesId;

    @ApiModelProperty(value= "附件的真实地址")
    private String fileUrl;

    @ApiModelProperty(value= "附件的真实存储地址")
//    @NotNull(message = "附件的真实地址",groups = {Insert.class})
    private String storageAddress;


    @ApiModelProperty(value= "附件题名")
//    @NotNull(message = "附件题名",groups = {Insert.class})
    private String attachamentTitle;
}
