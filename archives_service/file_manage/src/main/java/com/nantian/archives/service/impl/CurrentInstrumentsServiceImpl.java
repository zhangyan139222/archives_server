package com.nantian.archives.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nantian.archives.mapper.*;
import com.nantian.archives.service.CurrentInstrumentsService;
import com.nantian.security.mapper.UserMapper;
import com.natian.entity.archives.*;
import com.natian.entity.security.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class CurrentInstrumentsServiceImpl   extends ServiceImpl<CurrentInstrumentsMapper, CurrentInstrument>   implements CurrentInstrumentsService   {

    @Resource
    private CurrentInstrumentsMapper currentInstrumentsMapper;
    @Resource
    private FileOperationLoggingMapper fileOperationLoggingMapper;
    @Resource
    private UserFileMapper userFileMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private FileAttachmentMapper fileAttachmentMapper;

    /**
     * 获取所有的数据（组合条件+数据分页）
     *
     * @param
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<CurrentInstrument> getCurrentInstrumentList(Integer page, Integer size, String documentClassLevel) {
        Page<CurrentInstrument> pages = new Page<>(page, size);
        QueryWrapper<CurrentInstrument> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audit_status", 3);    //审核通过的数据才能进行归档
        if (StringUtils.isNotEmpty("documentClassLeve")) {
            queryWrapper.eq("document_class_level", documentClassLevel);
        }
        IPage<CurrentInstrument> currentInstrumentsIPage = currentInstrumentsMapper.selectPage(pages, queryWrapper);
        return currentInstrumentsIPage;
    }

    /**
     * 获取所有的数据（组合条件+数据分页）
     *
     * @param
     * @param page
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> getCurrentInstrumentListByUser(Integer page, Integer size, String userId, String documentClassLevel,  String auditStatus) {
        IPage<CurrentInstrument> iPage = null;
        Page<CurrentInstrument> pages = null;
        List<CurrentInstrument> currentInstruments = null;
        Map<String, Object> map = new HashMap<>();
        if (page != null && size != null) {
            pages = new Page<>(page, size);
        }
        User user = userMapper.selectById(userId);
        String applyUser=null;
        if (user.getLevel() != null && user.getLevel() == 0) {    //超级管理员（档案处管理员)获取所有的数据
            QueryWrapper<CurrentInstrument> queryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotEmpty(documentClassLevel)) {
                queryWrapper.likeRight("document_class_level", documentClassLevel);
            }
            if (StringUtils.isNotEmpty(auditStatus)) {
                queryWrapper.likeRight("audit_status", auditStatus);
            }
            queryWrapper.orderByDesc("create_time");

            if (pages == null) {
                currentInstruments = currentInstrumentsMapper.getAllListByAdmin(documentClassLevel);
                map.put("iPages", currentInstruments);
            } else {
                iPage = currentInstrumentsMapper.getAllListOfPageByAdmin(pages, documentClassLevel,applyUser,null);
                map.put("iPages", iPage);
            }
        } else if (user.getLevel() != null && user.getLevel() == 1) {       //其他各处室管理员
            if (pages == null) {
                currentInstruments = currentInstrumentsMapper.getAllListByDept(userId);
                map.put("iPages", currentInstruments);
            }

        } else {     //其他普通用户获取自己申请通过的数据及自己上传审核通过的数据
            if (pages == null) {
                try {
                    currentInstruments = currentInstrumentsMapper.getAllListByUser(userId, documentClassLevel, auditStatus);
                    map.put("iPages", currentInstruments);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    iPage = currentInstrumentsMapper.getAllListOfPageByUser(pages, userId, documentClassLevel,applyUser, auditStatus);
                    map.put("iPages", iPage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }


    /**
     * 获取左侧的数据分类树
     *
     * @return
     */

    @Override
    public List<Map<String, Object>> getLeftTree() {
        //获取父级list
        List<CurrentInstrument> parentlist = currentInstrumentsMapper.getLeftTreeParentNode();
        //获取第二级菜单
        List<CurrentInstrument> secondlist = currentInstrumentsMapper.getLeftTreeSecondNode();
        //获取第三级菜单
        List<CurrentInstrument> thirdList = currentInstrumentsMapper.getLeftTreeThirdNode();
        List<Map<String, Object>> listMap = new ArrayList<>();
        //先找到所有的一级分类
        for (CurrentInstrument currentInstrument : parentlist) {
            Map<String, Object> parentMap = new HashMap<>();
            parentMap.put("mid", currentInstrument.getTimeYear());
            parentMap.put("pid", "0");
            parentMap.put("mname", currentInstrument.getTimeYear());
            parentMap.put("children1", new ArrayList<>());
            //获取第二个节点数据
            getSecondChild(currentInstrument.getTimeYear(), parentMap, secondlist, thirdList);
            listMap.add(parentMap);
        }
        return listMap;

    }


    /**
     * 获取第二级、第三级菜单集合
     *
     * @param id         上一级比较的id值
     * @param parentMap  上一级map
     * @param secondlist 第二级菜单集合
     * @param thirdList  第三级菜单集合
     */
    private void getSecondChild(String id, Map<String, Object> parentMap, List<CurrentInstrument> secondlist, List<CurrentInstrument> thirdList) {
        List<Map<String, Object>> secondChildList = new ArrayList<>();
        if (secondlist != null && secondlist.size() > 0) {
            for (CurrentInstrument currentInstrument : secondlist) {
                Map<String, Object> childMap = new HashMap<>();
                if (currentInstrument.getTimeYear().equals(id)) {
                    childMap.put("pid", currentInstrument.getTimeYear());
                    childMap.put("mid", currentInstrument.getStoragePeriod());
                    childMap.put("mname", currentInstrument.getStoragePeriod());
                    childMap.put("children2", new ArrayList<>());
                    //获取第三个节点数据
                    getThirdChild(currentInstrument.getStoragePeriod(), childMap, thirdList);
                    secondChildList.add(childMap);
                }
            }
            parentMap.put("children1", secondChildList);

        }
    }


    /**
     * 获取第三级菜单集合
     *
     * @param id        上一级比较的id值
     * @param parentMap 上级的map
     * @param thirdList 第三级集合
     */
    private void getThirdChild(String id, Map<String, Object> parentMap, List<CurrentInstrument> thirdList) {
        List<Map<String, Object>> thirdChildList = new ArrayList<>();
        if (thirdList != null && thirdList.size() > 0) {
            for (CurrentInstrument currentInstrument : thirdList) {
                Map<String, Object> thirdMap = new HashMap<>();
                if (currentInstrument.getStoragePeriod().equals(id)) {
                    thirdMap.put("pid", currentInstrument.getStoragePeriod());
                    thirdMap.put("mid", currentInstrument.getBoxNumber());
                    thirdMap.put("mname", currentInstrument.getBoxNumber());
                    thirdChildList.add(thirdMap);
                }
            }
            parentMap.put("children2", thirdChildList);

        }
    }


    @Override
    public IPage<CurrentInstrument> getPermissionFile(Long page, Long size, String userId, String id) {

        Page<CurrentInstrument> pages = new Page<>(page, size);
        return currentInstrumentsMapper.getPermissionFile(pages, userId, id);

    }

    @Override
    public CurrentInstrument getCurrentInstrumentsDetail(String id,String permissionId) {

        CurrentInstrument currentInstrument = currentInstrumentsMapper.getCurrentInstrumentsDetail(id);
        //新增档案归档日志
        //更新查档状态
        UserFile  userFile=new UserFile();
        userFile.setId(permissionId);
        userFile.setAuditStatus(4);
        userFileMapper.updateById(userFile);
        FileOperationLogging fileOperationLogging = new FileOperationLogging();
        fileOperationLogging.setFileId(permissionId);
        fileOperationLogging.setUserId(currentInstrument.getUserId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
        fileOperationLogging.setOperName("查看档案归档数据详情");
        fileOperationLogging.setOperType(1);
        fileOperationLoggingMapper.insert(fileOperationLogging);
        return currentInstrument;
    }


    /**
     * 保存归档数据并添加归档日志
     *
     * @param currentInstrument
     * @return
     */
    @Override
    @Transactional
    public boolean saveCurrentInstruments(CurrentInstrument currentInstrument) {
        boolean  flag=false;
        currentInstrumentsMapper.insert(currentInstrument);
        //新增电子档案附件
        List<FileAttachment> fileAttachmentList = currentInstrument.getFileAttachmentList();
        if(fileAttachmentList!=null) {    //有电子档案的才需要新增附件
            for (FileAttachment fileAttachment : fileAttachmentList) {
                fileAttachment.setArchivesId(currentInstrument.getId());
                fileAttachmentMapper.insert(fileAttachment);
            }
        }
        //新增档案日志
        FileOperationLogging fileOperationLogging = new FileOperationLogging();
        fileOperationLogging.setFileId(currentInstrument.getId());
        fileOperationLogging.setUserId(currentInstrument.getUserId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
        fileOperationLogging.setOperName("新增档案归档数据");
        fileOperationLogging.setOperType(1);
        fileOperationLoggingMapper.insert(fileOperationLogging);
        flag=true;
        return flag;
    }

    @Override
    @Transactional
    public boolean updateCurrentInstruments(CurrentInstrument currentInstrument) {
           boolean  flag=false;
            CurrentInstrument byId = currentInstrumentsMapper.selectById(currentInstrument.getId());
            currentInstrument.setVersion(byId.getVersion());
            if (byId.getAuditStatus() == 2) {   //对于归档数据审核不通过，修改后重新更新审核状态
                currentInstrument.setAuditStatus(0);
            }
            currentInstrumentsMapper.updateById(currentInstrument);
            //修改电子档案附件
            List<FileAttachment> fileAttachmentList = currentInstrument.getFileAttachmentList();
            if(fileAttachmentList!=null) {    //电子档案可能为空
                for (FileAttachment fileAttachment : fileAttachmentList) {
                    fileAttachment.setArchivesId(currentInstrument.getId());
                    fileAttachmentMapper.insert(fileAttachment);
                }
            }
            //新增档案归档日志
            FileOperationLogging fileOperationLogging = new FileOperationLogging();
            fileOperationLogging.setFileId(currentInstrument.getId());
            fileOperationLogging.setUserId(currentInstrument.getUserId());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
            fileOperationLogging.setOperName("修改档案归档数据");
            fileOperationLogging.setOperType(1);
            fileOperationLoggingMapper.insert(fileOperationLogging);
            flag=true;
            return flag;


    }

    @Override
    @Transactional
    public boolean deleteCurrentInstruments(String id, String userId) {
            boolean  flag=false;
            currentInstrumentsMapper.deleteById(id);
            FileOperationLogging fileOperationLogging = new FileOperationLogging();
            fileOperationLogging.setFileId(id);
            fileOperationLogging.setUserId(userId);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
            fileOperationLogging.setOperName("删除档案归档数据");
            fileOperationLogging.setOperType(1);
            fileOperationLoggingMapper.insert(fileOperationLogging);
            flag=true;
            return flag;


    }

    @Override
    @Transactional
    public boolean updateExamine(String id, Integer auditStatus, String auditMind, String userId) {
           boolean flag=false;
            CurrentInstrument currentInstrument = new CurrentInstrument();
            currentInstrument.setId(id);
            currentInstrument.setAuditStatus(auditStatus);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            currentInstrument.setAuditTime(simpleDateFormat.format(new Date()));
            currentInstrumentsMapper.updateById(currentInstrument);
            FileOperationLogging fileOperationLogging = new FileOperationLogging();
            fileOperationLogging.setFileId(id);
            fileOperationLogging.setUserId(userId);
            fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
            if (auditStatus != null && auditStatus == 1) {
                fileOperationLogging.setOperName("档案归档数据审核通过");
            }
            if (auditStatus != null && auditStatus == 2) {
                fileOperationLogging.setOperName("档案归档数据审核不通过");
                fileOperationLogging.setOperDesc(auditMind);
            }
            fileOperationLogging.setOperType(1);
            fileOperationLoggingMapper.insert(fileOperationLogging);
            flag=true;
            return flag;


    }

    @Override
    @Transactional
    public boolean updateIsFile(String id, String documentClassLevel, String userId) {
           boolean result=false;
            CurrentInstrument currentInstrument = new CurrentInstrument();
            currentInstrument.setId(id);
            currentInstrument.setDocumentClassLevel(documentClassLevel);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            currentInstrument.setUpdateTime(simpleDateFormat.format(new Date()));
            CurrentInstrument byId = currentInstrumentsMapper.selectById(id);
            currentInstrument.setVersion(byId.getVersion());
            currentInstrument.setAuditStatus(3);
            int flag = currentInstrumentsMapper.updateById(currentInstrument);
            //添加档案归档日志
            FileOperationLogging fileOperationLogging = new FileOperationLogging();
            fileOperationLogging.setFileId(id);
            fileOperationLogging.setUserId(userId);
            fileOperationLogging.setFinishTime(simpleDateFormat.format(new Date()));
            if (flag != 0) {
                fileOperationLogging.setOperName("档案数据归档成功");
            } else {
                fileOperationLogging.setOperName("档案数据归档失败");
            }
            fileOperationLogging.setOperType(1);
            fileOperationLoggingMapper.insert(fileOperationLogging);
             result=true;
            return result;

    }




    @Override
    public Map<String, Object> getAllListByDay(String userId) {
        User user = userMapper.selectById(userId);
        List<CurrentInstrument> currentInstruments = null;
        List<UserFile> userFiles=null;
        Map<String,Object> map=new HashMap<>();
        if (user.getLevel() != null && user.getLevel() == 0) {     //档案处管理员
            currentInstruments = currentInstrumentsMapper.getListByAdminByDay();   //档案归档数据
             userFiles = userFileMapper.getUserFileByAdminByDay();
        } else if (user.getLevel() != null && user.getLevel() == 1) {   //各处室管理员

        } else {     //普通用户
            currentInstruments=currentInstrumentsMapper.getListByUserByDay(userId);
            userFiles= userFileMapper.getUserFileByUserByDay(userId);

        }
         map.put("currentInstruments",currentInstruments);
         map.put("userFiles",userFiles);

         return map;
    }

    @Override
    public  Map<String, Object>  getAllList(String userId,Long page, Long size, String applyUser,Integer applyType) {
        User user = userMapper.selectById(userId);
        Page<CurrentInstrument>  pages=new Page<>(page,size);
        Page<UserFile>  upages=new Page<>(page,size);
        String documentClassLevel=null;
        String auditStatus=null;
        Page<CurrentInstrument>  currentInstrumentPage=null;
        IPage<UserFile> userFile=null;
        Map<String,Object>  map=new HashMap<>();
        if(applyType!=null &&  applyType==1){    //归档数据
            if (user.getLevel() != null && user.getLevel() == 0) {   //管理员
                currentInstrumentPage = currentInstrumentsMapper.getAllListOfPageByAdmin(pages, documentClassLevel,applyUser,null);

            }else if (user.getLevel() != null && user.getLevel() == 1) {   //各处室管理员

            }else{      //普通用户
                currentInstrumentPage = currentInstrumentsMapper.getAllListOfPageByUser(pages, userId, documentClassLevel, applyUser, auditStatus);
            }
             map.put("resultList",currentInstrumentPage);
            }else if(applyType!=null &&  applyType==2){    //查档数据

            if (user.getLevel() != null && user.getLevel() == 0) {   //管理员
                try{
                    userFile = userFileMapper.getUserFileOfPageByAdmin(upages,applyUser);
                }catch (Exception  e){
                    e.printStackTrace();
                }


            }else if (user.getLevel() != null && user.getLevel() == 1) {   //各处室管理员

            }else{      //普通用户
                userFile= userFileMapper.getUserFileListByUserOfPage(upages,null);
            }
            map.put("resultList",userFile);

        }else{        //归档数据+查档数据
            if (user.getLevel() != null && user.getLevel() == 0) {   //管理员
                currentInstrumentPage = currentInstrumentsMapper.getAllListOfPageByAdmin(pages, documentClassLevel,applyUser,null);
                userFile = userFileMapper.getUserFileOfPageByAdmin(upages,applyUser);

            }else if (user.getLevel() != null && user.getLevel() == 1) {   //各处室管理员

            }else{      //普通用户
                userFile= userFileMapper.getUserFileListByUserOfPage(upages,null);
                currentInstrumentPage = currentInstrumentsMapper.getAllListOfPageByUser(pages, userId, documentClassLevel, applyUser, auditStatus);
            }
            map.put("userFile",userFile);
            map.put("currentInstrument",currentInstrumentPage);
        }
        return map;
    }


    @Override
    public Map<String,Object> getAllTotalByAudit() {
        return currentInstrumentsMapper.getAllTotalByAudit();
    }

    @Override
    public Map<String, Object>  getAllTotalByAuditByMonth() {
        return currentInstrumentsMapper.getAllTotalByAuditByMonth();
    }



}
