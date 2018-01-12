package com.alibaba.schedule.domin;


import java.util.Date;

/**
 * Created by muming on 16/5/24.
 */
public class MachineDO {

    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private Long groupId;
    private String ip;
    private MachineStatus status;
    private Date lastHeartbeat;

    @Override
    public String toString() {
        return "MachineDO{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", groupId=" + groupId +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                ", lastHeartbeat=" + lastHeartbeat +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public MachineStatus getStatus() {
        return status;
    }

    public void setStatus(MachineStatus status) {
        this.status = status;
    }

    public Date getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(Date lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }
}
