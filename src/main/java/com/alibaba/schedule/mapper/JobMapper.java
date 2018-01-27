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

import com.alibaba.schedule.domin.JobDO;


/**
 * @author dunhanyang
 */
@Repository
public interface JobMapper {

	@Insert("insert into t_job(gmt_create,gmt_modified,guid,descs,group_id,expect_status,conf,creator)"
			+ " values(#{job.gmtCreate},#{job.gmtModified},#{job.guid},#{job.desc},#{job.groupId},#{job.expectStatus},#{job.conf},#{job.creator})")
	public int add(@Param("job") JobDO jobDO);
	
	@Select("select * from t_job")
	@Results({
	      @Result(property="gmtCreate" , column="gmt_create"),
	      @Result(property="gmtModified",column="gmt_modified"),
	      @Result(property="groupId",column="group_id"),
	      @Result(property="expectStatus",column="expect_status")
	  })
	public List<JobDO> selectAll();
	@Delete("delete from t_job where id=#{id}")
	public void deleteById(@Param("id") long id);
	@Update("update t_job set expect_status=#{expectStatus}, gmt_modified = now() where id=#{id}")
	public int updateById(@Param("id") int id,@Param("expectStatus") int expectStatus);
	@Select("select gmt_create,gmt_modified,guid,desc,group_id,conf,creator from t_job where id=#{id}")
	public List<JobDO> queryById(@Param("id") long id);

	@Select("select * from t_job where group_id=#{groupId}")
	@Results({
	      @Result(property="gmtCreate" , column="gmt_create"),
	      @Result(property="gmtModified",column="gmt_modified"),
	      @Result(property="groupId",column="group_id"),
	      @Result(property="expectStatus",column="expect_status")
	  })
	public List<JobDO> queryByGroupId(Integer groupId);
}
