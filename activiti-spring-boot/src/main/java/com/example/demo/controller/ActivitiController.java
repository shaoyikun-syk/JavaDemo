package com.example.demo.controller;

import com.example.demo.util.SecurityUtil;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/18 13:07
 * @Description:
 */
@RestController
public class ActivitiController {

    @Autowired
    private ProcessRuntime processRuntime;//定义流程操作

    @Autowired
    private TaskRuntime taskRuntime;//任务相关操作

    @Autowired
    private SecurityUtil securityUtil;//工具类



    //查询任务，并完成任务
    @RequestMapping("hello")
    public void testTask(){

        //查询任务
        //securityUtil.logInAs("ryandawsonuk");//指定执行人   必须在同一组
        Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));
        if(taskPage.getTotalItems()>0){

            List<Task> content = taskPage.getContent();
            for (Task task : content) {
                System.out.println("任务："+task);

                //获取任务
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());

                //执行任务
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());



            }

        }


        taskPage=taskRuntime.tasks(Pageable.of(0,10));
        if(taskPage.getTotalItems()>0){
            for (Task task : taskPage.getContent()) {
                System.out.println("任务2："+task);
            }
        }



    }


}
