package com.xbyrh.service.impl;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.DeviceUserRef;
import com.xbyrh.repo.entity.DeviceUserRefExample;
import com.xbyrh.repo.mapper.DeviceMapper;
import com.xbyrh.repo.mapper.DeviceUserRefMapper;
import com.xbyrh.service.IDeviceService;
import com.xbyrh.service.IDeviceUserRefService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
@Service
public class DeviceUserRefServiceImpl implements IDeviceUserRefService {

    @Autowired
    private DeviceUserRefMapper deviceUserRefMapper;

    @Autowired
    private IDeviceService deviceService;

    @Override
    public List<Device> getDevicesByUid(Long uid) {

        DeviceUserRefExample example = new DeviceUserRefExample();
        example.createCriteria().andUidEqualTo(uid);

        List<DeviceUserRef> deviceUserRefList = deviceUserRefMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(deviceUserRefList)) {
            return new ArrayList<>();
        }

        List<Long> deviceIdList = deviceUserRefList.stream().map(DeviceUserRef::getDeviceId).collect(Collectors.toList());

        return deviceService.getDevicesByIds(deviceIdList);
    }

}
