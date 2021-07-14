package com.nantian.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.natian.entity.archives.CurrentInstrument;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface CurrentInstrumentsMapper  extends BaseMapper<CurrentInstrument> {
    List<CurrentInstrument> getLeftTreeParentNode();

    List<CurrentInstrument>   getLeftTreeSecondNode();

    List<CurrentInstrument>   getLeftTreeThirdNode();



    List<CurrentInstrument> getAllListByUser(
                                                           @Param("uesrId") String uesrId, @Param("documentClassLevel") String documentClassLevel,
                                                          /* @Param("title") String title, @Param("responsiblePerson") String responsiblePerson,
                                                           @Param("timeYear") String timeYear, @Param("archivalCode") String archivalCode,
                                                           @Param("storagePeriod") String storagePeriod, @Param("boxNumber") String boxNumber,*/
                                                           @Param("auditStatus") String auditStatus);



    Page<CurrentInstrument> getAllListOfPageByUser(IPage<CurrentInstrument> pages,
                                                           @Param("uesrId") String uesrId,
                                                           @Param("documentClassLevel") String documentClassLevel,
                                                          /* @Param("title") String title, @Param("responsiblePerson") String responsiblePerson,
                                                           @Param("timeYear") String timeYear, @Param("archivalCode") String archivalCode,
                                                           @Param("storagePeriod") String storagePeriod, @Param("boxNumber") String boxNumber,*/
                                                           @Param("applyUser") String  applyUser,
                                                           @Param("auditStatus") String auditStatus);


    Page<CurrentInstrument> getPermissionFile(IPage<CurrentInstrument> pages,
                                              @Param("userId")  String userId,
                                              @Param("id") String id);




    Page<CurrentInstrument>  getAllListOfPageByAdmin( @Param("pages")IPage<CurrentInstrument> pages,
                                                     @Param("documentClassLevel") String documentClassLevel,
                                                     @Param("applyUser") String applyUser,
                                                      @Param("auditStatus") String auditStatus);

    List<CurrentInstrument>  getAllListByAdmin(@Param("documentClassLevel") String documentClassLevel);

    CurrentInstrument   getCurrentInstrumentsDetail(@Param("id") String  id );


   List<CurrentInstrument> getAllListByDept(@Param("userId") String userId);

   List<CurrentInstrument> getListByAdminByDay();


   List<CurrentInstrument> getListByUserByDay(@Param("userId") String userId);


   Map<String,Object> getAllTotalByAudit();

    Map<String, Object>  getAllTotalByAuditByMonth();

}
