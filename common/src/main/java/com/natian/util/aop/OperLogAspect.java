package com.natian.util.aop;

import com.alibaba.fastjson.JSON;
import com.natian.entity.operation.OperationLogging;
import com.natian.util.annotation.OperLogging;
import com.natian.util.aop.operation.service.OperationLoggingService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/27 15:13
 * @Description:切面处理类，操作日志异常日志记录
 */
@Aspect
@Component
public class OperLogAspect {
    @Resource
   private OperationLoggingService  operationLoggingService;

    //定义切点,@Pointcut,设置操作日志切入点 记录操作日志 在注解的位置切入代码
//   @Pointcut("@annotation(com.natian.util.annotation.OperLogging)")
     @Pointcut("execution(* com.*.archives.controller.*.*(..))")
    public  void logPointCut(){

    }


    @Before("logPointCut()")
    public void beginTransaction() {
        System.out.println("请求方法前");
    }

    @After("logPointCut()")
    public void commit() {
        System.out.println("请求方法后");

    }

        @AfterReturning("logPointCut()")
    public   void saveSysOperLog(JoinPoint  joinPoint){
       //保存日志
        OperationLogging operationLogging=new OperationLogging();
        //从切面织入点处通过反射机制获取织入点的方法
        MethodSignature    signature= (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method   method  =signature.getMethod();
        //获取操作
        OperLogging  operLogging=method.getAnnotation(OperLogging.class);
        if(operLogging!=null){
            String operDesc=operLogging.OperDesc();
            String  operModul=operLogging.operModul();
            String operType=operLogging.operType();
            operationLogging.setOperModule(operModul);
        }

        //获取请求的类名
        String  calssName=joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName=method.getName();
        operationLogging.setMethodName(calssName+"."+methodName);

        //请求的参数
        Object[]  args=joinPoint.getArgs();
        //将参数所在的数组转为json
        String  param= JSON.toJSONString(args);
        operationLogging.setRequestParam(param);
        SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        operationLogging.setStartTime(simpleDateFormat.format(new Date()));

        operationLoggingService.save(operationLogging);

    }
}
