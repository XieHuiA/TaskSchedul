package com.alibaba.schedule.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.alibaba.schedule.domin.TaskDo;

@Repository
public interface TaskMapper {

	//查出所有Task
	@Select("select * from t_task")
	@Results({
		@Result(property="gmtCreate" , column="gmt_create"),
		@Result(property="gmtModified" , column="gmt_modified"),
		@Result(property="jobId" , column="job_id"),
		@Result(property="expectStatus" , column="expect_status"),
		@Result(property="actualStatus" , column="actual_status"),
		@Result(property="ip" , column="ip"),
		@Result(property="lastHeartbeat" , column="last_heartbeat")
})
	public List<TaskDo> findAll();

		//通过jobid查出Task
		@Select("select * from t_task where job_id=#{jobId}")
		@Results({
			@Result(property="gmtCreate" , column="gmt_create"),
			@Result(property="gmtModified" , column="gmt_modified"),
			@Result(property="expectStatus" , column="expect_status"),
			@Result(property="actualStatus" , column="actual_status"),
			@Result(property="ip" , column="ip"),
			@Result(property="lastHeartbeat" , column="last_heartbeat")
	})
	public TaskDo queryByJobid(String jobId);

		//通过Taskid查出task
		@Select("select * from t_task where id=#{taskid}")
		@Results({
			@Result(property="gmtCreate" , column="gmt_create"),
			@Result(property="gmtModified" , column="gmt_modified"),
			@Result(property="expectStatus" , column="expect_status"),
			@Result(property="actualStatus" , column="actual_status"),
			@Result(property="ip" , column="ip"),
			@Result(property="lastHeartbeat" , column="last_heartbeat")
	})
		public TaskDo queryTaskid(String taskid);

		//修改task的期望状态
		@Update("update t_task set ip=#{ip},expect_status=#{expectStatus},gmt_modified = now() where id=#{id}  ")
		public boolean updateExpectStatus(TaskDo task);

		//新增Task
		@Insert(" insert into t_task(gmt_create,gmt_modified,job_id,expect_status,actual_status,last_heartbeat,ip) "
				+ "values(#{gmtCreate},#{gmtModified},#{jobId},#{expectStatus},#{actualStatus},#{lastHeartbeat},#{ip})")
		public boolean addTaskByjobId(TaskDo task);

		//通过ip去查询Task
		@Select("select * from t_task where ip=#{ip}")
		@Results({
			@Result(property="gmtCreate" , column="gmt_create"),
			@Result(property="gmtModified" , column="gmt_modified"),
			@Result(property="expectStatus" , column="expect_status"),
			@Result(property="actualStatus" , column="actual_status"),
			@Result(property="ip" , column="ip"),
			@Result(property="lastHeartbeat" , column="last_heartbeat")
	})
		public List<TaskDo> queryByExpectIp(String ip);

		
		//修改实际状态 和期望状态
		@Update("update t_task set expect_status=#{expectStatus},actual_status=#{expectStatus},gmt_modified = #{gmtModified} where id=#{id}  ")
		public boolean updateActualStatue(TaskDo task);
}
