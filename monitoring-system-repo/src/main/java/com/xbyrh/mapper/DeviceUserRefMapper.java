package com.xbyrh.mapper;

import com.xbyrh.entity.DeviceUserRef;
import com.xbyrh.entity.DeviceUserRefExample;
import java.util.List;

public interface DeviceUserRefMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceUserRef record);

    int insertSelective(DeviceUserRef record);

    List<DeviceUserRef> selectByExample(DeviceUserRefExample example);

    DeviceUserRef selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceUserRef record);

    int updateByPrimaryKey(DeviceUserRef record);
}