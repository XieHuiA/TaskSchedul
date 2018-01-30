package com.alibaba.schedule.service;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.master.core.TaskSchedul;
import com.alibaba.schedule.GsonUtil;
import com.alibaba.schedule.conf.Config;
import com.alibaba.schedule.domin.TaskDo;
import com.alibaba.schedule.mapper.MachineMapper;
import com.alibaba.schedule.mapper.TaskMapper;

@Service
public class TaskService {
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private MachineMapper machineMapper;

	@Transactional
	public List<TaskDo> findAll() {
			return taskMapper.findAll();
		}

	@Transactional
	public TaskDo queryByJobid(String jobId){
		return taskMapper.queryByJobid(jobId);
		
	}
	
	@Transactional
	public TaskDo queryTaskid(String taskid){
		return taskMapper.queryTaskid(taskid);
	}
	
	
	@Transactional
	public boolean update(TaskDo task) {//id 期望状态
        if (task.getId() == null) {
            throw new IllegalArgumentException("task_id为空");
        }
        //修改task的期望状态
        return taskMapper.updateExpectStatus(task);
        
    }


	//新增Task
	@Transactional
	public boolean addTaskByjobId(TaskDo task) {
		//task.setJobId(id);
       // task.setExpectStatus(Config.RUNNING);
		task.setGmtCreate(new Date());
		task.setGmtModified(new Date());
		task.setActualStatus(0);
		task.setLastHeartbeat(new Date());
		return taskMapper.addTaskByjobId(task);
	}


	//通过ip去查询Task
	@Transactional
	public List<TaskDo> queryByExpectIp(String ip) {
		
		return taskMapper.queryByExpectIp(ip);
	}

	@Transactional
	public boolean updateActualStatue(TaskDo task) {
		
		return taskMapper.updateActualStatue(task);
	}
	
	
	
}

















