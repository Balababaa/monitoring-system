package com.xbyrh.repo.mapper;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.DeviceExample;
import java.util.List;

public interface DeviceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    int insert(Device record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    int insertSelective(Device record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    List<Device> selectByExample(DeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    Device selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    int updateByPrimaryKeySelective(Device record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device
     *
     * @mbggenerated Fri Apr 16 14:15:51 CST 2021
     */
    int updateByPrimaryKey(Device record);
}