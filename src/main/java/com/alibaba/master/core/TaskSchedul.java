package com.alibaba.master.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.schedule.service.JobService;
import com.alibaba.schedule.service.MachineService;
import com.alibaba.schedule.service.TaskService;

@Component
public class TaskSchedul {
	/**
	 * Task Schedul service
	 */
	private ScheduledExecutorService loadingConfService;
	
	private ScheduledExecutorService scheduleService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private MachineService machineService;
	@Autowired
	private JobService jobService;
	/**
	 * 1.集群调度准备相关信息查询。
	 * 2.进行计算，然后根据每台服务器上的任务总数的大小进行对比计算。
	 */
	
	public void init (){
		this.loadingConfService = Executors.newScheduledThreadPool(1);
		this.scheduleService = Executors.newScheduledThreadPool(1);
	}
	/**
	 * 环境：mysql jdk1.8 springboot dayahub/oss  linux 
	 * 1.task schedul 
	 *   1).先查表，对比状态
	 *   2).调度 
	 *   3).
	 */
	
	public ScheduledExecutorService getLoadingConfService() {
		return loadingConfService;
	}
	public void scedule(){
		scheduleService.scheduleWithFixedDelay(()->{
			
			
		}, 20, 300, TimeUnit.SECONDS);
	}
	
	
	
}
