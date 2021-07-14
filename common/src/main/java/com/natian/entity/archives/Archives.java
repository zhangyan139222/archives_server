package com.natian.entity.archives;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.Release;
import com.natian.doamin.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
@TableName("d_file366")
public class Archives {
    //ID
    @TableId(value="id",type =IdType.ASSIGN_UUID)
    @Null(message = "不可指定id",groups = Insert.class)
    @NotNull(message = "需要指定id",groups = {Update.class, Delete.class})
    @ApiModelProperty(value= "记录标识")
    private String  id;
    //档号
    @NotNull(message = "需要指定档号",groups = {Insert.class, Update.class})
    @ApiModelProperty(value="档号")
    @TableField("KEYWORD")
    private String archivalCode;
    //案卷号
    @TableField(value = "F1")
    @ApiModelProperty(value="案卷号")
    private String fileNo;
    //卷内顺序号
    @TableField(value = "F2")
    @ApiModelProperty(value="卷内顺序号")
    private String inVolumeNumber;
    //盒号
    @TableField(value = "HH")
    @ApiModelProperty(value="盒号")
    private String  boxNumber;
    //件号
    @TableField(value = "JH")
    @ApiModelProperty(value="件号")
    private String partNumber;
    //题名
    @TableField(value = "TITLE")
    @ApiModelProperty(value="题名")
    @Size(min = 1,max = 500,message = "题名长度需在1-500之间",groups = {Insert.class, Update.class})
    @NotNull(message = "题名不能为空",groups = {Insert.class,Update.class})
    @Null(message = "不需要指定题名",groups = {Delete.class})
    private String  title;
    //分类号
    @TableField(value = "FLH")
    @ApiModelProperty(value="分类号")
    @Size(min = 1,max = 200,message = "分类号长度需在1-500之间",groups = {Insert.class, Update.class})
    @NotNull(message = "分类号",groups = {Insert.class,Update.class})
    @Null(message = "不需要指定分类号",groups = {Delete.class})
    private String classNumber;
    //分类名称
    @TableField(value = "FLNAME")
    @ApiModelProperty(value="分类名称")
    @Size(min = 1,max = 200,message = "分类名称长度需在1-500之间",groups = {Insert.class, Update.class})
    @NotNull(message = "分类名称",groups = {Insert.class,Update.class})
    @Null(message = "不需要分类名称",groups = {Delete.class})
    private String className;
    //责任者
    @TableField(value = "ZRZ")
    @ApiModelProperty(value="责任者")
    private String  responsiblePerson;
    //页数
    @TableField(value = "YS")
    @ApiModelProperty(value="页数")
    private String  pageAmount;
    //原文件日期
    @TableField(value = "CWRQ")
    @ApiModelProperty(value="原文件日期")
    private String origDocumentDate;
    //归档份数
    @TableField(value = "GDFS")
    @ApiModelProperty(value="归档份数")
    private String archivalNumber;
    //归档日期
    @TableField(value = "GDRQ")
    @ApiModelProperty(value="归档日期")
    private String archivalTime;
    //密级
    @TableField(value = "MJ")
    @ApiModelProperty(value="密级")
    private String  secretDegree;
    //保管期限
    @TableField(value = "BGQX")
    @ApiModelProperty(value="保管期限")
    @Size(min = 1,max = 200,message = "保密期限长度需在1-500之间",groups = {Insert.class, Update.class})
    @NotNull(message = "保密期限不能为空",groups = {Insert.class,Update.class})
    private String storagePeriod;
    //主题词
    @TableField(value = "ZTC")
    @ApiModelProperty(value="主题词")
    private String themeWord;
    //年度
    @TableField(value = "ND")
    @ApiModelProperty(value="年度")
    @Size(min = 1,max = 200,message = "年份长度需在1-500之间",groups = {Insert.class, Update.class})
    @NotNull(message = "年份不能为空",groups = {Insert.class,Update.class})
    @JsonFormat(pattern = "yyyy")
    private String timeYear;
    //备注
    @TableField(value = "BZ")
    @ApiModelProperty(value="备注")
    private String remarks;
    //整编人
    @TableField(value = "ZBR")
    @ApiModelProperty(value="整编人")
    private String reorganizer;
    //归档人
    @TableField(value = "GDR")
    @ApiModelProperty(value="归档人")
    private String fillingPerson;
    //整编日期
    @TableField(value = "ZBRQ")
    @ApiModelProperty(value="整编日期")
    private String reorganizerDate;
    //全宗号
    @TableField(value = "QZH")
    @ApiModelProperty(value="全宗号")
    private String generalArchivalNumber;
    //所属卷号
    @TableField(value = "OWNERVOL")
    @ApiModelProperty(value="所属卷号")
    private String volumesNo;
    //文件编号
    @TableField(value = "F3")
    @ApiModelProperty(value="文件编号")
    private String documentNumber;
    //案卷档号
    @TableField(value = "F6")
    @ApiModelProperty(value="案卷档号")
    private String  fileNumber;
    //载体类型
    @TableField(value = "F9")
    @ApiModelProperty(value="载体类型")
    private  String carrierType;
    //稿本
    @TableField(value = "F12")
    @ApiModelProperty(value="稿本")
    private  String  manuscript;
    //控制标识
    @TableField(value = "F14")
    @ApiModelProperty(value="控制标识")
    private  String  controlSign;;
    //目录号
    @TableField(value = "F15")
    @ApiModelProperty(value="目录号")
    @Size(min = 1,max = 200,message = "目录号长度需在1-200之间",groups = {Insert.class, Update.class})
    @NotNull(message = "目录号不能为空",groups = {Insert.class,Update.class})
    @Null(message = "目录号不需要指定",groups = {Delete.class})
    private  String  catalogNumber;
    //全宗名称
    @TableField(value = "F16")
    @ApiModelProperty(value="全宗名称")
    @Size(min = 1,max = 500,message = "全宗号长度需在1-500之间",groups = {Insert.class, Update.class})
    @NotNull(message = "全宗号不能为空",groups = {Insert.class,Update.class})
    @Null(message = "全宗号不需要指定",groups = {Delete.class})
    private  String  fileName;
    //起页号
    @TableField(value = "F4")
    @ApiModelProperty(value="起页号")
    private  String  startPage;
    //止页号
    @TableField(value = "F7")
    @ApiModelProperty(value="止页号")
    private  String endPage ;
    //文件日期
    @TableField(value = "F8")
    @ApiModelProperty(value="文件日期")
    private  String  documentDate;
    //日期备注
    @TableField(value = "F11")
    @ApiModelProperty(value="日期备注")
    private  String dateRemarks ;
    //原案卷号
    @TableField(value = "F5")
    @ApiModelProperty(value="原案卷号")
    private  String origFileNo;
    //原卷内顺序号
    @TableField(value = "F10")
    @ApiModelProperty(value="原卷内顺序号")
    private  String origInVolumeNumber ;
    //原档号
    @TableField(value = "F13")
    @ApiModelProperty(value="原档号")
    private  String  originalDocumentNumber;

    //原页数
    @TableField(value = "F17")
    @ApiModelProperty(value="原页数")
    private  String  origPageAmount;
    //库位号
    @TableField(value = "F18")
    @ApiModelProperty(value="库位号")
    private  String  locationNo;

    //审核状态
    @TableField(value="audit_status")
    @ApiModelProperty(value="审核状态")
    @Null(message = "不需审核状态标识",groups = {Insert.class,Update.class,Delete.class})
    @NotNull(message = "请指定审核状态标识，0或1",groups = Release.class)
    private Integer auditStatus;
    //数据审核时间
    @TableField(value="audit_time")
    @ApiModelProperty(value="数据审核时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Null(message = "不可传入审核时间",groups = {Insert.class,Update.class,Delete.class})
    @NotNull(message = "请指定启用标识，0或1",groups = Release.class)
    private  String auditTime;

}
