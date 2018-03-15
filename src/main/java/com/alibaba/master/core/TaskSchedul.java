package com.alibaba.master.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.schedule.conf.Config;
import com.alibaba.schedule.conf.Statuss;
import com.alibaba.schedule.domin.JobDO;
import com.alibaba.schedule.domin.MachineDO;
import com.alibaba.schedule.domin.TaskDo;
import com.alibaba.schedule.exception.InternalErrorException;
import com.alibaba.schedule.exception.ShrekException;
import com.alibaba.schedule.service.JobService;
import com.alibaba.schedule.service.MachineService;
import com.alibaba.schedule.service.TaskService;

@Component
public class TaskSchedul {

	private static final Logger LOG = LoggerFactory.getLogger(TaskSchedul.class);
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
	@Autowired
	private Config conf;

	/**
	 * 1.集群调度准备相关信息查询。 2.进行计算，然后根据每台服务器上的任务总数的大小进行对比计算。
	 */

	public void init() {
		this.loadingConfService = Executors.newScheduledThreadPool(1);
		this.scheduleService = Executors.newScheduledThreadPool(1);
	}

	/**
	 * 环境：mysql jdk1.8 springboot dayahub/oss linux 
	 * task schedul 
	 * 1).先查表，对比状态
	 * 2).调度 
	 * 3).任务分配。状态修改
	 */

	public ScheduledExecutorService getLoadingConfService() {
		return loadingConfService;
	}

	public void scedule() {
		scheduleService.scheduleWithFixedDelay(() -> {
			LOG.info("taskschedul start...");
			// 1.查询所有job
			List<JobDO> jobs = jobService.selectAll();
			// 遍历job
			if (jobs == null || jobs.isEmpty()) {
				LOG.info("no jobs to schedule");
				return;
			}
			for (JobDO job : jobs) {
				LOG.info("job [{}] start schedule", job.getId());// job开始

				// checkStatus();

				try {
					scheduleJob(job);
				} catch (Exception e) {
					LOG.error("schedule job failed", e);
				}

				LOG.info("job [{}] finish schedule", job.getId());
			}
			LOG.info("schedule done.");

		}, 0, 15, TimeUnit.SECONDS);
	}

	public boolean checkTaskIsNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	public void scheduleJob(JobDO job) {

		TaskDo task = taskService.queryByJobid(String.valueOf(job.getId()));
		if (job.getExpectStatus() == Statuss.STOPPED) {
			stopTasks(task);
			LOG.info("job {} is stopped", job.getId());
			return;

		} else if (job.getExpectStatus() == Statuss.RUNNING) {
			if (!checkTaskIsNull(task)) {
				if (task.getExpectStatus() != Statuss.RUNNING) {
					startTask(task, job);
				}

			} else {
				// this status is running about create time
				createTaskByJobId(job);
				task = taskService.queryByJobid(String.valueOf(job.getId()));
				startTask(task, job);
			}
			LOG.info("job {} is running", job.getId());
		} else if (job.getExpectStatus() == Statuss.RESTARTING) {
			// 1). to stop 2).to start
			LOG.info("job {} will restart", job.getId());

			if (!checkTaskIsNull(task)) {
				stopTasks(task);
				startTask(task, job);
			} else {
				createTaskByJobId(job);
				// 通过jobid在去查询task
				TaskDo taskjobid = taskService.queryByJobid(String.valueOf(job.getId()));
				if (!checkTaskIsNull(taskjobid)) {
					stopTasks(taskjobid);
					startTask(taskjobid, job);
				}

			}

			// task.setExpectStatus(Statuss.RESTARTING);
			// taskService.update(task);
		}
		LOG.info("job {} schedule success !", job.getId());

	}

	private boolean createTaskByJobId(JobDO job) {
		String ip = chooseIP(job);
		TaskDo task = new TaskDo();
		task.setJobId(job.getId());
		task.setIp(ip);
		task.setExpectStatus(Statuss.RUNNING);

		return taskService.addTaskByjobId(task);

	}

	// taskMap key=machine_ip value=task_通过ip查出
	private String chooseIP(JobDO job) {
		// TODO Auto-generated method stub
		List<MachineDO> macList = machineService.selectAll();
		// 过滤一下，过滤掉超时 只保留在线的worker
		List<MachineDO> timeoutMachines = machineService.queryByTimeoutValue(conf.MACHINE_TIMEOUT_TIME);

		macList.removeAll(timeoutMachines);

		Map<String, List<TaskDo>> taskMap = buildTaskMap(macList);
		return chooseMachine(job, macList, taskMap);
	}

	private Map<String, List<TaskDo>> buildTaskMap(List<MachineDO> machineDOs) {
		Map<String, List<TaskDo>> taskDOMap = new HashMap<>();
		for (MachineDO machineDO : machineDOs) {
			taskDOMap.put(machineDO.getIp(), taskService.queryByExpectIp(machineDO.getIp()));
		}
		return taskDOMap;
	}

	public String chooseMachine(JobDO job, List<MachineDO> machineGroup, Map<String, List<TaskDo>> taskMap) {

		int minTaskSize = 0;
		long minJobTaskSize = 0;
		String ip = "";
		// taskMap key=machine_ip value=task_通过ip查出
		for (MachineDO machineDO : machineGroup) {
			List<TaskDo> machineTasks = taskMap.get(machineDO.getIp());
			if (machineTasks == null) {
				machineTasks = new ArrayList<>();
			}
			int taskSize = machineTasks.size();
			long jobTaskSize = machineTasks.stream().filter(taskDO -> taskDO.getJobId() == job.getId()).count();

			if ((StringUtils.isBlank(ip)) || (jobTaskSize < minJobTaskSize)
					|| (jobTaskSize == minJobTaskSize && taskSize < minTaskSize)) {
				minTaskSize = taskSize;
				minJobTaskSize = jobTaskSize;
				ip = machineDO.getIp();
			}
		}
		return ip;
	}

	// 把Task的期望状态改成running
	private boolean startTask(JobDO job, TaskDo task) {
		String ip = chooseIP(job);
		task.setIp(ip);
		task.setExpectStatus(Statuss.RUNNING);
		job.setExpectStatus(1);
		jobService.updateById(Integer.valueOf(job.getId().toString()), Statuss.RUNNING);
		return taskService.update(task);
	}

	// 启动task
	private void startTask(TaskDo task, JobDO job) {

		if (task.getExpectStatus() != Statuss.RUNNING) {
			startTask(job, task);
		}
	}

	// 把Task的期望状态改成stopped
	private boolean stopTask(TaskDo task) {
		task.setExpectStatus(Statuss.STOPPED);
		return taskService.update(task);
	}

	// 停止task
	private void stopTasks(TaskDo task) {
		if (checkTaskIsNull(task)) {
			return;
		}
		if (task.getExpectStatus() != Statuss.STOPPED) {

			if (!stopTask(task)) {
				LOG.warn("stop task failed, task: {}", task.getId());// 停止任务失败
				throw new InternalErrorException(String.format("stop task failed, task: %d", task.getId()));
			}
		}
	}

	/*
	 * private void checkStatus() { if (isStop) {//isStop = false;
	 * LOG.warn("schedule thread stopping");//调度线程停止 throw new
	 * ShrekException(conf.ERROR_CODE_INVALID_OPERATION,
	 * "schedule thread stopping");//调度线程停止 } }
	 */

	//

}
