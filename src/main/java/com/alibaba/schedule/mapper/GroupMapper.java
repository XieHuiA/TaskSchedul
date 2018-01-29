package com.alibaba.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.alibaba.schedule.domin.GroupDO;

@Repository
public interface GroupMapper {
  
	//查出所有Task
		@Select("select * from t_group")
		@Results({
			@Result(property="gmtCreate" , column="gmt_create"),
			@Result(property="gmtModified" , column="gmt_modified"),
			@Result(property="name" , column="name"),
			@Result(property="desc" , column="desc")
	})
	public List<GroupDO> findAll();

		
}
