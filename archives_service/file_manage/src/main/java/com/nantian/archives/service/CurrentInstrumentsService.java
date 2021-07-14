package com.nantian.archives.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.natian.entity.archives.CurrentInstrument;
import com.natian.entity.archives.UserFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CurrentInstrumentsService   extends IService<CurrentInstrument> {

  /**
   * 获取所有的数据列表并实现分页
   *
   *
   */
IPage<CurrentInstrument>  getCurrentInstrumentList(Integer  page,Integer  size,String documentClassLeve);


  Map<String,Object>  getCurrentInstrumentListByUser(Integer page, Integer size,String uesrId,String documentClassLevel,String auditStatus);


  List<Map<String,Object>> getLeftTree();

  IPage<CurrentInstrument> getPermissionFile(Long page,Long  size, String userId, String id);

  CurrentInstrument   getCurrentInstrumentsDetail(String  id,String permissionId );


   boolean  saveCurrentInstruments(CurrentInstrument  currentInstrument);


   boolean  updateCurrentInstruments(CurrentInstrument  currentInstrument);

   boolean  deleteCurrentInstruments(String id,String userId);

   boolean updateExamine(String id,Integer auditStatus, String auditMind,String userId);

   boolean updateIsFile(String id,String documentClassLevel,String userId);


    Map<String, Object>   getAllListByDay(String userId);

    Map<String, Object>   getAllList(String userId,Long page, Long size, String applyUser,Integer applyType);

    Map<String,Object>getAllTotalByAudit();


    Map<String, Object>   getAllTotalByAuditByMonth();




}
