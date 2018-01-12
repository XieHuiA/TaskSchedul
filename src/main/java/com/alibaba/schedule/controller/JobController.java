package com.alibaba.schedule.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.schedule.domin.JobDO;
import com.alibaba.schedule.service.JobService;




/**
 * 
 * @author dunhanyang
 *
 */
@RestController
public class JobController {

	@Autowired
	JobService service;
	//添加job数据
	@RequestMapping("/job/add")
	public String add(){
		JobDO job=new JobDO();
		job.setConf("{"+"\"key\":"+"\"value\""+"}");
		job.setCreator("someone");
		job.setDesc("dfdf");
		job.setExpectStatus(0);
		job.setGmtCreate(new Date());
		job.setGmtModified(new Date());
		job.setGroupId((long) 2);
		job.setGuid("numoo3");
		service.add(job);
		return "SUCCESS";
	}
	
	@RequestMapping("/job/update")
	public String updateById(){
		
		
		return "SUCCESS";
	}
	@RequestMapping("/job/delete")
	public String deleteById(){
		return "SUCCESS";
	}
	
	@RequestMapping("/job/query")
	public String queryById(){
		
		
		
		return "SUCCESS";
	}
	//查询全部job数据
	@RequestMapping("/job/selectAll")
	public List<JobDO> selectAll(){
		return service.selectAll();
	}
	
	
}
