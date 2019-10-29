package com.example.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/17 18:34
 * @Description:
 */


//启动流程实例   第二步
public class ActivitiStartInstance {

    /**
     * 影响的表
     * act_hi_actinst           已经完成的活动
     * act_hi_identitylink      参与者信息
     * act_hi_procinst          流程实例
     * act_hi_taskinst          任务实例
     *
     * act_ru_execution         执行表
     * act_ru_identitylink      参与者信息
     * act_ru_task              任务
     *
     *
     *
     *
     *
     */
    public static void main(String[] args) {
        //1.创建
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.创建
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //3.创建流程实例  需要知道   act_re_procdef表中 的KEY_
        //myProcess_1
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");

        //4.输出实例的相关信息
        System.out.println("流程部署ID"+processInstance.getDeploymentId());
        System.out.println("流程实例ID"+processInstance.getId());
        System.out.println("活动ID"+processInstance.getActivityId());


    }



}








