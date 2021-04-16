package com.xbyrh.service;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.User;

import java.util.List;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public interface IDeviceService {

    Device getDeviceById(Long id);

    List<Device> getDevicesByIds(List<Long> idList);


}
