package com.example.demo;

import com.example.demo.util.SecurityUtil;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


	@Autowired
	private ProcessRuntime processRuntime;//定义流程操作

	@Autowired
	private TaskRuntime taskRuntime;//任务相关操作

	@Autowired
	private SecurityUtil securityUtil;//工具类


    //流程定义信息的查看：
	@Test
	public void testDefinition() {

		securityUtil.logInAs("salaboy");//登录信息 security认证工作


		//注意：流程部署的工作在activiti7余springboot整合后，会自动部署resources/processes/*。bpmn


		//分页查询出流程定义信息
		Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));

		System.out.println("已经部署的流程个数"+processDefinitionPage.getTotalItems());

		List<ProcessDefinition> content = processDefinitionPage.getContent();//当前部署的每一个流程定义信息
		for (ProcessDefinition p : content) {
			System.out.println(p);
		}


	}



	//启动流程实例
    @Test
    public void testStartInstance() {

	    /*
	    * .withProcessInstanceName("Processing Content:" + content)
                .withVariable("content", content)
	    * */


        securityUtil.logInAs("salaboy");//登录信息 security认证工作
        ProcessInstance start = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("myProcess_1")
                .build());


        System.out.println(start.getId());//6abcdb93-f145-11e9-9e8e-005056c00001


    }



    //查询任务，并完成任务
    @Test
    public void testTask(){

        //查询任务
        securityUtil.logInAs("ryandawsonuk");//指定执行人   必须在同一组
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








