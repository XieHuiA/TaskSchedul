package com.alibaba.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.alibaba.schedule.domin.MachineDO;


@Repository
public interface MachineMapper {

	//查询所有machine
	@Select("select * from t_machine")
	@Results({
			@Result(property="gmtCreate" , column="gmt_create"),
			@Result(property="gmtModified" , column="gmt_create"),
			@Result(property="groupId" , column="group_id"),
			@Result(property="lastHeartbeat" , column="last_heartbeat")
	})
	public List<MachineDO> selectAll();

	 //根据id查出machine
	@Select("select * from t_machine where id =#{id}")
	@Results({
		@Result(property="gmtCreate" , column="gmt_create"),
		@Result(property="gmtModified" , column="gmt_create"),
		@Result(property="groupId" , column="group_id"),
		@Result(property="lastHeartbeat" , column="last_heartbeat")
})
	public MachineDO findbyId(String id);

	//根据id删除machine
	@Delete("delete from t_machine where id=#{id}")
	public int deleteById(@Param("id") String id);

	//添加machine
	@Insert(" insert into t_machine(gmt_create,gmt_modified,group_id,ip,status,last_heartbeat) "
			+ "values(#{gmtCreate},#{gmtModified},#{groupId},#{ip},#{status},#{lastHeartbeat})")
	public int add(MachineDO machineDO);

	//修改machine
	@Update("update t_machine set last_heartbeat=now(),gmt_modified=now() where ip=#{ip}  ")
	public boolean updateByIp(@Param("ip") String ip);

	//根据ip查出machine
	@Select("select * from t_machine where ip =#{ip}")
	@Results({
		@Result(property="gmtCreate" , column="gmt_create"),
		@Result(property="gmtModified" , column="gmt_create"),
		@Result(property="groupId" , column="group_id"),
		@Result(property="lastHeartbeat" , column="last_heartbeat")
})
	public MachineDO findbyip(String ip);
	
	//查出超时的machine
		@Select("select * from t_machine where timestampdiff(SECOND,`last_heartbeat`,now()) > #{machineTimeoutSec}")
		@Results({
			@Result(property="gmtCreate" , column="gmt_create"),
			@Result(property="gmtModified" , column="gmt_create"),
			@Result(property="groupId" , column="group_id"),
			@Result(property="lastHeartbeat" , column="last_heartbeat")
	})
	public List<MachineDO> queryByTimeoutValue(int machineTimeoutSec);
}















