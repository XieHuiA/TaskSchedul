package com.alibaba.schedule.domin;

import java.util.Date;

/**
 * Created by muming on 16/5/31.
 */
public class JobDO {

    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private String guid;
    private String desc;
    private Long groupId;
    private Status expectStatus;
    private String conf;
    private String creator;

    @Override
    public String toString() {
        return "JobDO{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", guid='" + guid + '\'' +
                ", desc='" + desc + '\'' +
                ", groupId=" + groupId +
                ", expectStatus=" + expectStatus +
                ", conf='" + conf + '\'' +
                ", creator='" + creator + '\'' +
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Status getExpectStatus() {
        return expectStatus;
    }

    public void setExpectStatus(Status expectStatus) {
        this.expectStatus = expectStatus;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}

