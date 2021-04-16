package com.xbyrh.repo.entity;

import java.util.Date;

public class Device {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device.id
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device.device_name
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    private String deviceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device.device_type
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    private Integer deviceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device.http_flv_url
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    private String httpFlvUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device.is_delete
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device.create_time
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device.update_time
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.id
     *
     * @return the value of device.id
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.id
     *
     * @param id the value for device.id
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.device_name
     *
     * @return the value of device.device_name
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.device_name
     *
     * @param deviceName the value for device.device_name
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.device_type
     *
     * @return the value of device.device_type
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public Integer getDeviceType() {
        return deviceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.device_type
     *
     * @param deviceType the value for device.device_type
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.http_flv_url
     *
     * @return the value of device.http_flv_url
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public String getHttpFlvUrl() {
        return httpFlvUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.http_flv_url
     *
     * @param httpFlvUrl the value for device.http_flv_url
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public void setHttpFlvUrl(String httpFlvUrl) {
        this.httpFlvUrl = httpFlvUrl == null ? null : httpFlvUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.is_delete
     *
     * @return the value of device.is_delete
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.is_delete
     *
     * @param isDelete the value for device.is_delete
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.create_time
     *
     * @return the value of device.create_time
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.create_time
     *
     * @param createTime the value for device.create_time
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device.update_time
     *
     * @return the value of device.update_time
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device.update_time
     *
     * @param updateTime the value for device.update_time
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}