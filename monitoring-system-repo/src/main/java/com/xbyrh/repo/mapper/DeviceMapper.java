package com.xbyrh.repo.mapper;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.DeviceExample;
import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}