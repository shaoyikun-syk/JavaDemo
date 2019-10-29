package com.example.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/17 15:57
 * @Description:
 */


//部署  第一步
public class ActivitiDeployment {

    /**
     *
     * 影响的表
     * act_ge_bytearray     bpmn文件及png图像
     * act_re_deployment    流程定义的部署信息
     * act_re_procdef       流程定义的信息
     *
     *
     * act_ge_property    ：部署的时候已经添加数据
     *
     *
     */


    //流程定义部署
    public static void main(String[] args) {
        //1.创建
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到实例
        RepositoryService repositoryService = processEngine.getRepositoryService();





        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday.bpmn")
                .addClasspathResource("diagram/holiday.png")
                .name("请假申请流程")
                .deploy();


        //4.输出
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }

}

















