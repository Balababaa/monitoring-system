package com.xbyrh.web.controller;

import com.xbyrh.common.context.AuthContext;
import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.model.mapper.DeviceMapper;
import com.xbyrh.repo.model.vo.DeviceVO;
import com.xbyrh.service.IDeviceUserRefService;
import com.xbyrh.web.model.dto.DeviceDTO;
import com.xbyrh.web.model.params.DeviceListParam;
import com.xbyrh.web.model.params.DeviceParam;
import com.xbyrh.web.model.support.BaseResponse;
import com.xbyrh.web.model.support.PaginationResponse;
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
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private IDeviceUserRefService deviceUserRefService;

    @Autowired
    private DeviceMapper deviceMapper;

    @GetMapping("list")
    public PaginationResponse<DeviceVO> deviceList(DeviceListParam deviceListParam) {
        User user = AuthContext.getUser();
        //todo 分页

        if(deviceListParam!=null) {
            // todo 分页
            List<Device> deviceList = deviceUserRefService.getDevicesByUid(user.getUid(), deviceListParam.getDeviceName(), deviceListParam.getDeviceType());
            return PaginationResponse.ok(20L, deviceMapper.toVOList(deviceList));
        }

        return PaginationResponse.ok(20L,deviceMapper.toVOList(deviceUserRefService.getDevicesByUid(user.getUid())));
    }

    @PostMapping("add")
    public BaseResponse<String> addDevice(@RequestBody DeviceParam deviceParam){


        return BaseResponse.ok("");
    }

}
