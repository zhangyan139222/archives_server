package com.natian.util.annotation;

import java.lang.annotation.*;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/27 14:51
 * @Description:自定义日志操作注解
 */

@Target(ElementType.METHOD)  //注解放置的目标位置，METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME)   //注解在哪个阶段执行
@Documented
public @interface OperLogging {
    String operModul()   default "";   //操作模块
    String operType()   default "";   //操作类型
    String OperDesc()   default"";   //操作说明
}
