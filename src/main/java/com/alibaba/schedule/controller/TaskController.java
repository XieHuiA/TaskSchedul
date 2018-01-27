package com.alibaba.schedule.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.schedule.conf.Config;
import com.alibaba.schedule.conf.Statuss;
import com.alibaba.schedule.domin.TaskDo;
import com.alibaba.schedule.service.TaskService;
@RequestMapping("/task")
@RestController
public class TaskController {
    
	@Autowired
	private TaskService taskService;
	
	/**
	 * 查出所有Task
	 * @return
	 */
	 @RequestMapping(value = "/findall")
	 public List<TaskDo> findAll() {
		 
		 return taskService.findAll();
	 }
	 
	 @RequestMapping(value = "/queryByJobid")
	 public TaskDo queryByJobid(String jobId) {
		 
		 return taskService.queryByJobid(jobId);
	 }

	 @RequestMapping(value = "/queryTaskid")
	 public TaskDo queryTaskid(String id) {
		 
		 return taskService.queryTaskid(id);
	 } 
	 
	 @RequestMapping(value = "/update")
	 public boolean update(TaskDo task) {//id 期望状态
		 	task.setId((long)2);
	        task.setExpectStatus(0);
	        return taskService.update(task);
	        
	    }
	 
	 @RequestMapping(value = "/addTaskByjobId")
	 public void addTaskByjobId(TaskDo task) {//id 期望状态
		 	task.setJobId(1);
		 	task.setExpectStatus(Statuss.RUNNING);
	       taskService.addTaskByjobId(task);
	        
	    }
	 
	//通过ip去查询Task
	 @RequestMapping(value = "/queryByExpectIp")
		public List<TaskDo> queryByExpectIp(String ip) {
			
			return taskService.queryByExpectIp(ip);
		}
	 
	 @RequestMapping(value = "/updateActualStatue")
	 public boolean updateActualStatue(TaskDo task) {
		 	task.setId(6l);
			task.setActualStatus(2);
			task.setExpectStatus(2);
			task.setGmtModified(new Date());
			return taskService.updateActualStatue(task);
		}
}
