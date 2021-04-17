package com.xbyrh.repo.mapper;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.DeviceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> getDevicesByUid(@Param("uid") Long uid, @Param("deviceName") String deviceName,
                                 @Param("deviceType") Integer deviceType, @Param("offset") Long offset,
                                 @Param("limit") Long limit);

    Long countByUserId(@Param("uid") Long uid, @Param("deviceName") String deviceName,
                       @Param("deviceType") Integer deviceType);
}