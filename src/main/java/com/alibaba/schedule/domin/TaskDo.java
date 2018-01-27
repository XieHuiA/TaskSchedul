package com.alibaba.schedule.domin;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class TaskDo {
	
	private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private long jobId;
    private Integer expectStatus;
    private Integer actualStatus;
  //  private String expectIp;
    private String ip;
    private Date lastHeartbeat;

   

    

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public Integer getExpectStatus() {
		return expectStatus;
	}

	public void setExpectStatus(Integer expectStatus) {
		this.expectStatus = expectStatus;
	}

	public Integer getActualStatus() {
		return actualStatus;
	}

	public void setActualStatus(Integer actualStatus) {
		this.actualStatus = actualStatus;
	}


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(Date lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

	@Override
	public String toString() {
		return "TaskDo [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", jobId=" + jobId
				+ ", expectStatus=" + expectStatus + ", actualStatus=" + actualStatus + ", ip=" + ip
				+ ", lastHeartbeat=" + lastHeartbeat + "]";
	}
	
    
}
