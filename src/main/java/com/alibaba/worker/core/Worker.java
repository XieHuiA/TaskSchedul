package com.alibaba.worker.core;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.schedule.conf.Config;
import com.alibaba.schedule.conf.Statuss;
import com.alibaba.schedule.domin.TaskDo;
import com.alibaba.schedule.service.JobService;
import com.alibaba.schedule.service.MachineService;
import com.alibaba.schedule.service.TaskService;

@Component
public class Worker {
	private static final Logger LOG = LoggerFactory.getLogger(Worker.class);
	@Autowired
	public TaskService taskService;

	@Autowired
	JobService jobService;
	@Autowired
	Config conf ;
	public static Map<Long,WorkerCore> map = new HashMap<>();
	
	@Autowired
	public MachineService machineService;

	

	public void schedule() {
		// query task tables about task
		LOG.info("worker start schedule ");
		List<TaskDo> taskList = taskService.queryByExpectIp(conf.WORKER_IP);
		
		for (TaskDo task : taskList) {
			// to schedule task by expectStatus
			if (task.getExpectStatus()==Statuss.STOPPED) {
				stop(task);
				changeStatus(Statuss.STOPPED, Statuss.STOPPED,task);
				LOG.info("task {} is stoped ok.", task.getId());
			} else if (task.getExpectStatus()==Statuss.RUNNING) {

				if (task.getActualStatus()==Statuss.RUNNING) {
					// 正常 直接输出日志
					checkWorkerCoreStatus(task);
					LOG.info("task {} is run ok.", task.getId());
				} else if (task.getActualStatus()==Statuss.STOPPED) {
					start(task);
					changeStatus(Statuss.RUNNING, Statuss.RUNNING,task);
					LOG.info("task {} is run ok.", task.getId());
				}

			} else if (task.getExpectStatus()==Statuss.RESTARTING) {
				stop(task);
				start(task);
				jobService.updateById(Integer.valueOf(String.valueOf(task.getJobId())), Statuss.RUNNING);
				changeStatus(Statuss.RUNNING, Statuss.RUNNING, task);
				LOG.info("task {} is reStart ok.", task.getId());
			}
		}
	}

	private void checkWorkerCoreStatus(TaskDo task) {
		WorkerCore workerCore = map.get(task.getId());
		if (workerCore == null) {
			start(task);
		}
	}

	private void changeStatus(int status, int expectStatus, TaskDo task) {
		// TODO Auto-generated method stub
		task.setActualStatus(status);
		task.setGmtModified(new Date());
		
		taskService.updateActualStatue(task);
	}

	private void start(TaskDo task) {
		// 调用worker的start方法
		map.put(task.getId(), new WorkerCore());
		map.get(task.getId()).start("task--" + task.getId());

	}

	private void stop(TaskDo task) {
		// 调用worker的stop 方法
		WorkerCore workerCore = map.get(task.getId());
		if (workerCore!=null) {
			workerCore.stop();
		}

	}

	public boolean updateLastHeartBeat(String ip) {
		
		return machineService.updateByIp(ip);
	}
}
