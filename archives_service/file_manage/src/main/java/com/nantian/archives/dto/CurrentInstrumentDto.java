package com.nantian.archives.dto;

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
import java.io.Serializable;


@Data
public class CurrentInstrumentDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private  String id;

  //全宗号
  @ApiModelProperty(value= "全宗号")
  private  String generalArchivalNumber;

  //全宗名称
  @ApiModelProperty(value= "全宗名称")
  private  String  generalArchivalName;

  //保管期限
  @ApiModelProperty(value= "保管期限")
  private String storagePeriod;

  //机构问题
  @ApiModelProperty(value= "机构问题")
  private String institutionalIssues;

  //年度
  @ApiModelProperty(value= "年度")
  private String timeYear;

  //件号
  @ApiModelProperty(value= "件号")
  private String partNumber;

  @ApiModelProperty(value= "档号")
  private String   archivalCode;

  //题名
  @ApiModelProperty(value= "题名")
  private String title;

  //责任者
  @ApiModelProperty(value= "责任者")
  private String responsiblePerson;

  //主题词
  @ApiModelProperty(value= "主题词")
  private String themeWord;

  //文件编号
  @ApiModelProperty(value= "文件编号")
  private String  documentNumber;

  //时间
  @ApiModelProperty(value= "时间")
  private String datetime;

  //时间备注
  @ApiModelProperty(value= "时间备注")
  private String timeremarks;

  //抄送
  @ApiModelProperty(value= "抄送")
  private String copyDelivery;

  //主送
  @ApiModelProperty(value= "主送")
  private   String mainDelivery;

  //稿本
  @ApiModelProperty(value= "稿本")
  private String manuscript;

  //紧急程度
  @ApiModelProperty(value= "紧急程度")
  private String urgencyDegree;

  @ApiModelProperty(value= "页数")
  private Integer pageAmount;

  //页号
  @ApiModelProperty(value= "页号")
  private Integer pageNumber;

  //归档份数
  @ApiModelProperty(value= "归档份数")
  private String archivalNumber;

  //盒号
  @ApiModelProperty(value= "盒号")
  private String  boxNumber;

  //密级
  @ApiModelProperty(value= "密级")
  private String secretDegree;

  //保密期限
  @ApiModelProperty(value= "保密期限")
  private String secrecyTerm;

  //原件号
  @ApiModelProperty(value= "原件号")
  private  String  originalNumber;

  //人名索引
  @ApiModelProperty(value= "人名索引")
  private String nameIndex;

  //备注
  @ApiModelProperty(value= "备注")
  private String remarks;

  //原档号
  @ApiModelProperty(value= "原档号")
  private String originalFileNumber;

  //原时间
  @ApiModelProperty(value= "原时间")
  private Integer originalTime;

  //原页数
  @ApiModelProperty(value= "原页数")
  private String originalFilePage;

  @ApiModelProperty(value= "附件题名")
  private String attachamentTitle;

  @ApiModelProperty(value= "当前存储地址")
  private  String storageAddress;

}
