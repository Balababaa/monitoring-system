package com.xbyrh.service.impl;

import com.xbyrh.common.context.AuthContext;
import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.DeviceExample;
import com.xbyrh.repo.entity.DeviceUserRefExample;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.mapper.DeviceMapper;
import com.xbyrh.repo.model.bo.DeviceBO;
import com.xbyrh.repo.model.params.DeviceAddParam;
import com.xbyrh.service.IDeviceService;
import com.xbyrh.service.IDeviceUserRefService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private IDeviceUserRefService deviceUserRefService;

    @Override
    public Device getDeviceById(Long id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Device> getDevicesByIds(List<Long> idList) {
        DeviceExample example = new DeviceExample();
        example.createCriteria().andIdIn(idList).andIsDeleteEqualTo(0);

        return deviceMapper.selectByExample(example);
    }

    @Override
    public List<Device> getDevicesByUid(Long uid, String deviceName, Integer deviceType, Long page, Long limit) {
        List<Device> deviceList = deviceMapper.getDevicesByUid(uid, deviceName, deviceType, (page - 1) * limit, limit);

        if (CollectionUtils.isEmpty(deviceList)) {
            return new ArrayList<>();
        }

        return deviceList;
    }

    @Override
    public Long countByUserId(Long uid, String deviceName, Integer deviceType) {
        return deviceMapper.countByUserId(uid, deviceName, deviceType);
    }

    @Override
    public void save(DeviceBO deviceBO) {
        User user = AuthContext.getUser();
        if (deviceBO.getId() != null) {
            Device device = getDeviceById(deviceBO.getId());
            device.setDeviceName(deviceBO.getDeviceName());
            device.setDeviceType(deviceBO.getDeviceType());
            device.setHttpFlvUrl(deviceBO.getHttpFlvUrl());
            device.setModifier(user.getUid());

            deviceMapper.updateByPrimaryKey(device);
        }else{
            Device device = new Device();

            device.setDeviceName(deviceBO.getDeviceName());
            device.setDeviceType(deviceBO.getDeviceType());
            device.setHttpFlvUrl(deviceBO.getHttpFlvUrl());
            device.setModifier(user.getUid());
            device.setCreator(user.getUid());

            deviceMapper.insertSelective(device);

            deviceUserRefService.addRef(device.getId(), user.getUid());
        }
    }
}
