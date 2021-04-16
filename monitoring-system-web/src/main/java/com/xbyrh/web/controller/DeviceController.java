package com.xbyrh.web.controller;

import com.xbyrh.common.context.AuthContext;
import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.User;
import com.xbyrh.service.IDeviceUserRefService;
import com.xbyrh.web.model.dto.DeviceDTO;
import com.xbyrh.web.model.mapper.DeviceMapper;
import com.xbyrh.web.model.params.DeviceListParam;
import com.xbyrh.web.model.params.DeviceParam;
import com.xbyrh.web.model.support.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */

@RestController
@RequestMapping("device")
public class DeviceController {

    @Autowired
    private IDeviceUserRefService deviceUserRefService;

    @Autowired
    private DeviceMapper deviceMapper;

    @GetMapping("list")
    public List<DeviceDTO> deviceList(Integer deviceType) {
        User user = AuthContext.getUser();
        List<Device> deviceList = deviceUserRefService.getDevicesByUid(user.getUid());

        if (deviceType != null) {
            deviceList = deviceList.stream().filter(device -> device.getDeviceType().equals(deviceType)).collect(Collectors.toList());
        }

        return deviceMapper.toDTOList(deviceList);
    }

    @PostMapping("add")
    public BaseResponse<String> addDevice(@RequestBody DeviceParam deviceParam){


        return BaseResponse.ok("");
    }

}
