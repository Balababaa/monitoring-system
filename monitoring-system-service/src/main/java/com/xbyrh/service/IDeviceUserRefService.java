package com.xbyrh.service;

import com.xbyrh.repo.entity.Device;

import java.util.List;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
public interface IDeviceUserRefService {

    List<Device> getDevicesByUid(Long uid);

}
