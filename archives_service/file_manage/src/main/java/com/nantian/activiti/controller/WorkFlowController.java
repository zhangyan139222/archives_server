package com.nantian.activiti.controller;

//import com.natian.entity.activiti.FileDataTask;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.activiti.api.process.runtime.ProcessRuntime;
//import org.activiti.engine.*;
//import org.activiti.engine.repository.DeploymentBuilder;
//import org.activiti.engine.repository.ProcessDefinitionQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;


//@RestController
//@Api(value = "/workflow",tags = {"数据流程审批"})
public class WorkFlowController {
  /*  @Autowired
    private RuntimeService   runtimeService;
    @Autowired
    private TaskService    taskService;    //任务相关的操作类
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private ProcessRuntime   processRuntime;  //实现流程定义相关的操作



    @PostMapping("/create")
    @ApiOperation("创建流程")
    public void startApply(String userId, FileDataTask fileDataTask){
        //定义资源文件
        String   bpmnPath= "processes/FileApplication.bpmn";
        String   imgPath= "processes/FileApplication.png";
        //创建部署构造器
        DeploymentBuilder   deploymentBuilder=repositoryService.createDeployment();
        //添加资源
        deploymentBuilder.addClasspathResource(bpmnPath);
//        deploymentBuilder.addClasspathResource(imgPath);
        //执行部署
        deploymentBuilder.deploy();
        //验证是否部署成功
        ProcessDefinitionQuery  query=repositoryService.createProcessDefinitionQuery();
       long count= query.processDefinitionKey("FileApplication").count();
//        Assert.isTrue(count==1);
        System.out.println("count***"+count);
    }
*/
}
