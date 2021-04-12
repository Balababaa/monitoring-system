package com.xbyrh.entity;

import java.util.Date;

public class Device {
    private Long id;

    private String deviceName;

    private Integer deviceType;

    private String httpFlvUrl;

    private Integer idDelete;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getHttpFlvUrl() {
        return httpFlvUrl;
    }

    public void setHttpFlvUrl(String httpFlvUrl) {
        this.httpFlvUrl = httpFlvUrl == null ? null : httpFlvUrl.trim();
    }

    public Integer getIdDelete() {
        return idDelete;
    }

    public void setIdDelete(Integer idDelete) {
        this.idDelete = idDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}