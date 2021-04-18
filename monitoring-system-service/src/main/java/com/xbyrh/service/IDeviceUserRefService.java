package com.xbyrh.service;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.DeviceUserRef;

import java.util.List;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
public interface IDeviceUserRefService {

    List<Device> getDevicesByUid(Long uid);

    void addRef(Long deviceId, Long uid);

    DeviceUserRef getDeviceByDeviceIdAndUid(Long id, Long uid);

    void update(DeviceUserRef deviceUserRef);

    void updateByPrimaryKey(DeviceUserRef deviceUserRef);
}
