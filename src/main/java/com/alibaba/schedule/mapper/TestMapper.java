package com.alibaba.schedule.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.alibaba.schedule.domin.JobDO;
import com.alibaba.schedule.domin.TaskDo;


@Repository
public interface TestMapper {

	@Select("select * from t_job " )
	public List<JobDO> selectAll();
	
}
