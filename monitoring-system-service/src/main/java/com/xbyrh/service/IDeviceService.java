package com.xbyrh.service;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.model.bo.DeviceBO;
import com.xbyrh.repo.model.params.DeviceAddParam;

import java.util.List;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public interface IDeviceService {

    Device getDeviceById(Long id);

    List<Device> getDevicesByIds(List<Long> idList);

    List<Device> getDevicesByUid(Long uid, String deviceName, Integer deviceType, Long page, Long limit);

    Long countByUserId(Long uid, String deviceName, Integer deviceType);

    void save(DeviceBO deviceBO);
}
