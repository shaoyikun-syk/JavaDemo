package com.example.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/17 19:17
 * @Description:
 */


//  第三步
public class ActivitiTaskQuery {


    public static void main(String[] args) {
        //1.创建
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //1.创建
        TaskService taskService = processEngine.getTaskService();

        //3.根据流程定义key，负责人assignee来实现当前用户的任务列表查询
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee("张三")
                .list();

        //4.任务列表的展示
        for (Task task : list) {
            System.out.println("流程实例ID"+task.getProcessInstanceId());
            System.out.println("任务ID"+task.getId());
            System.out.println("任务负责人"+task.getAssignee());
            System.out.println("任务名称"+task.getName());
        }

    }


}
