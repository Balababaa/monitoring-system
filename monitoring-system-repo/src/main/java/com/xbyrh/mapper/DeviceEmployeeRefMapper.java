package com.xbyrh.mapper;

import com.xbyrh.entity.DeviceEmployeeRef;
import com.xbyrh.entity.DeviceEmployeeRefExample;
import java.util.List;

public interface DeviceEmployeeRefMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceEmployeeRef record);

    int insertSelective(DeviceEmployeeRef record);

    List<DeviceEmployeeRef> selectByExample(DeviceEmployeeRefExample example);

    DeviceEmployeeRef selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceEmployeeRef record);

    int updateByPrimaryKey(DeviceEmployeeRef record);
}