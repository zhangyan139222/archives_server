package com.nantian.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/21 9:11
 * @Description:
 */
@Component
public class MyMetaObjectHandler  implements MetaObjectHandler {
//    @Resource
//    SimpleDateFormat simpleDateFormat;
    SimpleDateFormat   simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public void insertFill(MetaObject metaObject) {
        //新增时间
        this.setFieldValByName("createTime",simpleDateFormat.format(new Date()),metaObject);
        //修改时间
        this.setFieldValByName("updateTime",simpleDateFormat.format(new Date()),metaObject);
        //版本默认值
        this.setFieldValByName("version",1,metaObject);
        //删除标识默认值
        this.setFieldValByName("deleted",0,metaObject);
        //发布
        this.setFieldValByName("release",0,metaObject);
        //审核
        this.setFieldValByName("review",0,metaObject);
        //启用
        this.setFieldValByName("status",0,metaObject);

        //审核状态
        this.setFieldValByName("auditStatus",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",simpleDateFormat.format(new Date()),metaObject);
    }
}
