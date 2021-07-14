package com.natian.entity.archives;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.natian.doamin.Delete;
import com.natian.doamin.Insert;
import com.natian.doamin.Release;
import com.natian.doamin.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@TableName("d_file_current_instruments")
public class CurrentInstrument implements Serializable {
  private static final long serialVersionUID = 1L;
   //ID
  @TableId(value="id",type =IdType.ASSIGN_UUID)
  @Null(message = "不可指定id",groups = Insert.class)
  @NotNull(message = "需要指定id",groups = {Update.class, Delete.class,Release.class})
  @ApiModelProperty(value= "记录标识")
  private String  id;
  //档号
  @ApiModelProperty(value= "档号")
  @TableField(value = "archival_code")
  @NotNull(message = "档号不能为空",groups = {Insert.class,Update.class})
  @Null(message = "不需要指定档号",groups = {Delete.class})
  private String   archivalCode;
  //原件号
  @TableField(value = "original_number")
  @ApiModelProperty(value= "原件号")
  private  String  originalNumber;
  //件号
  @TableField(value = "part_number")
  @ApiModelProperty(value= "件号")
  private String partNumber;
  //说明题目文字
  @TableField(value = "captions")
  @ApiModelProperty(value= "说明题目文字")
  private String captions;
  //父题名
  @TableField(value = "parent_title")
  @ApiModelProperty(value= "父题名")
  private String ParentTitle;
  //并列题目
  @TableField(value = "parallel_topics")
  @ApiModelProperty(value= "并列题目")
  private String parallelTopics;
  //题名
  @Size(min = 1,max = 500,message = "题名长度需在1-500之间",groups = {Insert.class, Update.class})
  @NotNull(message = "题名不能为空",groups = {Insert.class,Update.class})
  @Null(message = "不需要指定题名",groups = {Delete.class})
  @TableField(value = "title")
  @ApiModelProperty(value= "题名")
  private String title;
  //分类号
  @TableField(value = "document_class_level")
//  @Size(min = 1,max = 100,message = "分类号长度需在1-200之间",groups = {Insert.class, Update.class})
//  @NotNull(message = "分类号",groups = {Insert.class,Update.class})
  @Null(message = "不需要指定分类号",groups = {Delete.class})
  @ApiModelProperty(value= "分类号")
  private String documentClassLevel;
  //分类名称
  @TableField(value = "document_class_name")
  @Size(min = 1,max = 200,message = "分类名称长度需在1-200之间",groups = {Insert.class, Update.class})
//  @NotNull(message = "分类名称",groups = {Insert.class,Update.class})
  @Null(message = "不需要分类名称",groups = {Delete.class})
  @ApiModelProperty(value= "分类名称")
  private String documentClassName;
  //责任者
  @TableField(value = "responsible_person")
  @ApiModelProperty(value= "责任者")
  private String responsiblePerson;
  //页号
  @TableField(value = "page_number")
  @ApiModelProperty(value= "页号")
  private Integer pageNumber;
  //页数
  @TableField(value = "page_amount")
  @ApiModelProperty(value= "页数")
  private Integer pageAmount;
  //原时间
  @TableField(value = "original_time")
  @ApiModelProperty(value= "原时间")
  private Integer originalTime;
  //归档份数
  @TableField(value = "archival_number")
  @ApiModelProperty(value= "归档份数")
  private String archivalNumber;
  //归档日期
  @TableField(value = "archival_time")
  @ApiModelProperty(value= "归档日期")
  private String archivalTime;
  //保密期限
  @TableField(value = "secrecy_term")
//  @Size(min = 1,max = 200,message = "保密期限长度需在1-200之间",groups = {Insert.class, Update.class})
//  @NotNull(message = "保密期限不能为空",groups = {Insert.class,Update.class})
  @ApiModelProperty(value= "保密期限")
  private String secrecyTerm;
  //抄送
  @TableField(value = "copy_delivery")
  @ApiModelProperty(value= "抄送")
  private String copyDelivery;
  //主送
  @TableField(value = "main_delivery")
  @ApiModelProperty(value= "主送")
  private   String mainDelivery;
  //紧急程度
  @TableField(value = "urgency_degree")
  @ApiModelProperty(value= "紧急程度")
  private String urgencyDegree;
  //密级
  @TableField(value = "secret_degree")
  @ApiModelProperty(value= "密级")
  private String secretDegree;
  //保管期限
  @TableField(value = "storage_period")
  @ApiModelProperty(value= "保管期限")
  private String storagePeriod;
  //摘要
  @TableField(value = "summary")
  @ApiModelProperty(value= "摘要")
  private String summary;
  //关键字
  @TableField(value = "keywords")
  @ApiModelProperty(value= "关键字")
  private  String keywords;
  //主题词
  @TableField(value = "theme_word")
  @ApiModelProperty(value= "主题词")
  private String themeWord;
  //年度
  @TableField(value = "time_year")
  @Size(min = 1,max = 200,message = "年份长度需在1-200之间",groups = {Insert.class, Update.class})
  @NotNull(message = "年份不能为空",groups = {Insert.class,Update.class})
  @JsonFormat(pattern = "yyyy")
  @ApiModelProperty(value= "年度")
  private String timeYear;
  //备注
  @TableField(value = "remarks")
  @ApiModelProperty(value= "备注")
  private String remarks;
  //脱机载体地址
  @TableField(value = "off_line_address")
  @ApiModelProperty(value= "脱机载体地址")
  private String offLineAddress;
  //脱机载体编号
  @TableField(value = "off_line_number")
  @ApiModelProperty(value= "脱机载体编号")
  private String offLineNumber;
  //当前存储地址
  @TableField(value = "storage_address")
  @ApiModelProperty(value= "当前存储地址")
  private  String storageAddress;
  //稿本
  @TableField(value = "manuscript")
  @ApiModelProperty(value= "稿本")
  private String manuscript;
  //整编人
  @TableField(value = "reorganizer")
  @ApiModelProperty(value= "整编人")
  private String reorganizer;
  //归档人
  @TableField(value = "filling_person")
  @ApiModelProperty(value= "归档人")
  private   String fillingPerson;
   //整编日期
   @TableField(value = "reorganize_time")
   @ApiModelProperty(value= "整编日期")
  private String reorganizeTime;
  //全宗号
  @TableField(value = "general_archival_number")
  @Size(min = 1,max = 500,message = "全宗号长度需在1-500之间",groups = {Insert.class, Update.class})
  @NotNull(message = "全宗号不能为空",groups = {Insert.class,Update.class})
  @Null(message = "全宗号不需要指定",groups = {Delete.class})
  @ApiModelProperty(value= "全宗号")
  private  String generalArchivalNumber;
  //全宗名称
  @TableField(value = "general_archival_name")
  @ApiModelProperty(value= "全宗名称")
  private  String  generalArchivalName;
  //机构问题
  @TableField(value = "institutional_issues")
  @Size(min = 1,max = 200,message = "机构问题长度需在1-200之间",groups = {Insert.class, Update.class})
  @NotNull(message = "机构问题不能为空",groups = {Insert.class,Update.class})
  @ApiModelProperty(value= "机构问题")
  private String institutionalIssues;
  //文件编号
  @TableField(value = "document_number")
  @ApiModelProperty(value= "文件编号")
  private String  documentNumber;
  //附件题名
  @TableField(value = "attachament_title")
  @ApiModelProperty(value= "附件题名")
  private String attachamentTitle;
  //人名索引
  @TableField(value = "name_index")
  @ApiModelProperty(value= "人名索引")
  private String nameIndex;
  //盒号
  @TableField(value = "box_number")
  @ApiModelProperty(value= "盒号")
  private String  boxNumber;
  //时间
  @TableField(value = "datetime")
  @ApiModelProperty(value= "时间")
//  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//  @Null(message = "不可传入时间",groups = {Insert.class,Update.class,Delete.class})
  private String datetime;
  //时间备注
  @TableField(value = "timeremarks")
  @ApiModelProperty(value= "时间备注")
  private String timeremarks;
  //原档号
  @TableField(value = "original_file_number")
  @ApiModelProperty(value= "原档号")
  private String originalFileNumber;
  //原页数
  @TableField(value = "original_file_page")
  @ApiModelProperty(value= "原页数")
  private String originalFilePage;


  //审核状态
  @TableField(value="audit_status",fill = FieldFill.INSERT)
  @Null(message = "不需审核状态标识",groups = {Insert.class,Delete.class})
  @NotNull(message = "请指定审核状态标识，0或1",groups = Release.class)
  @ApiModelProperty(value= "审核状态")
  private Integer auditStatus;

  //数据审核时间
  @TableField(value="audit_time",fill = FieldFill.INSERT)
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//  @Null(message = "不可传入审核时间",groups = {Insert.class,Update.class,Delete.class,Release.class})
//  @NotNull(message = "需要传入审核时间",groups = {Release.class})
  @ApiModelProperty(value= "数据审核时间")
  private  String auditTime;

  @ApiModelProperty(value= "附件实际地址")
  @TableField(value = "file_url")
  private   String   fileUrl;

  @ApiModelProperty(value= "用户ID")
  @TableField(value = "user_id")
//  @Null(message = "用户ID",groups = {Insert.class})
  private    String userId;

 //创建时间
  @TableField(value="create_time",fill = FieldFill.INSERT)
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @Null(message = "不可传入创建时间",groups = {Insert.class,Update.class,Delete.class,Release.class})
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


  @ApiModelProperty("是否有电子档案")
  private    Integer  isEFile;



  @TableField(exist = false)
  private List<FileAttachment> fileAttachmentList;



}
