package com.nantian.util.aop;

import com.alibaba.fastjson.JSON;
import com.nantian.util.annotation.OperLogging;
import com.nantian.util.aop.operation.service.OperationLoggingService;
import com.natian.entity.operation.OperationLogging;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
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
@Aspect   //创建当前类为切面类
@Component
public class OperLogAspect {
    @Resource
   private OperationLoggingService operationLoggingService;

    //定义切点,@Pointcut,设置操作日志切入点 记录操作日志 在注解的位置切入代码
//   @Pointcut("@annotation(com.nantian.util.annotation.OperLogging)")
//    @Pointcut("execution(public * com.nantian.archives.*.*.controller.*.*(..))")
//    @Pointcut("execution(public * com.nantian.archives.controller..*.*(..))")
//    public  void logPointCut(){
//
//    }

//    @Pointcut("execution(public * com.nantian.archives.controller..*.*(..))")
//    public void webLog(){}

//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable{
//        System.err.println("----AOP测试");
//    }
//
//    @AfterReturning(returning = "ret",pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        System.err.println("----请求完成");
//    }



//    @Pointcut("logPointCut()")
//    public void before(){
//        System.out.println("-----before-------");
//    }



//    @Around("logPointCut()")
//    public void around() {
//        System.out.println("-----around-------");
//    }

//    @AfterReturning(returning = "ret",pointcut = "logPointCut()")
//@AfterReturning(returning = "ret", pointcut = "webLog()")
public   void saveSysOperLog(JoinPoint  joinPoint,Object ret){
    System.out.println("方法的返回值********"+ret);
       //保存日志
        OperationLogging operationLogging=new OperationLogging();
        //从切面织入点处通过反射机制获取织入点的方法
        MethodSignature    signature= (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method   method  =signature.getMethod();
        //获取操作
        OperLogging operLogging=method.getAnnotation(OperLogging.class);
        if(operLogging!=null){
            String operDesc=operLogging.OperDesc();
            String  operModul=operLogging.operModul();
            String operType=operLogging.operType();
            operationLogging.setOperModule(operModul);
            operationLogging.setOperDesc(operDesc);
            operationLogging.setOperType(operType);
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
