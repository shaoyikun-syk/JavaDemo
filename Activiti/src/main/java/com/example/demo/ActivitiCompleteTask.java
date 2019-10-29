package com.example.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/17 19:34
 * @Description:
 */



//第四步
public class ActivitiCompleteTask {

    public static void main(String[] args) {

        //1.创建
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.创建
        TaskService taskService = processEngine.getTaskService();


        //3.处理任务  当前用户对任务的处理
        taskService.complete("2505");



    }


}












