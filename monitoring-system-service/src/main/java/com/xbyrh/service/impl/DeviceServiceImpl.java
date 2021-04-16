package com.xbyrh.service.impl;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.DeviceExample;
import com.xbyrh.repo.mapper.DeviceMapper;
import com.xbyrh.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Device getDeviceById(Long id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Device> getDevicesByIds(List<Long> idList) {
        DeviceExample example = new DeviceExample();
        example.createCriteria().andIdIn(idList);

        return deviceMapper.selectByExample(example);
    }
}
