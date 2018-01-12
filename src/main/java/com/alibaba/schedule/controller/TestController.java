package com.alibaba.schedule.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.schedule.domin.JobDO;
import com.alibaba.schedule.mapper.TestMapper;

@RestController
public class TestController {
	
	@Autowired
	TestMapper mapper ;
	
	@RequestMapping("/hello/test")
	public List<JobDO> queryAll(){
		List<JobDO> test = mapper.selectAll();
		for (JobDO string : test) {
			System.out.println(string);
		}
		return test;
	}
	//
	public String updateById(){
		return "SUCCESS";
	}
	
	public String deleteById(){
		return "SUCCESS";
	}
	
	public String add(){
		return "SUCCESS";
	}
	
	public String queryById(){
		return "SUCCESS";
	}
}
