package com.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/17 14:00
 * @Description:
 */
public class TestDemo {

    /**
     * 创建表（25张）
     */
    @Test
    public void testGenTable(){

        //1.创建ProcessEngineConfiguration
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.
                createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //第一个参数配置文件名称(activiti.cfg.xml)        第二个bean的id



        //2.创建ProcessEngine
        ProcessEngine processEngine = configuration.buildProcessEngine();

        //3.打印输出
        System.out.println(processEngine);


    }

    @Test
    public void testGenTableEasy(){
        //条件 名称：activiti.cfg.xml  bean的id  processEngineConfiguration

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }

}














