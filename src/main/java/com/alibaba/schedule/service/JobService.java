package com.alibaba.schedule.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.schedule.domin.JobDO;
import com.alibaba.schedule.mapper.JobMapper;



@Service
public class JobService {

	@Autowired
	JobMapper mapper;
	//添加job数据
	@Transactional
	public int add(JobDO job) {		
		return mapper.add(job);		
	}
   //查询所有job数据
	public List<JobDO> selectAll(){	
		return mapper.selectAll();
	}
	public void deleteById(long id){
		mapper.deleteById(id);
	}
	public void updateById(long id,String creator){
		mapper.updateById(id, creator);
		
	}
	//按id查询job数据
	public List<JobDO> queryById(Long id){
		return mapper.queryById(id);
	}
}
