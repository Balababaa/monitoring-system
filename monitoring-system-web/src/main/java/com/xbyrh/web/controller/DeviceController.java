package com.xbyrh.web.controller;

import com.xbyrh.service.context.AuthContext;
import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.model.bo.DeviceBO;
import com.xbyrh.repo.model.mapper.DeviceMapper;
import com.xbyrh.repo.model.params.DeviceAddParam;
import com.xbyrh.repo.model.params.DeviceDeleteParam;
import com.xbyrh.repo.model.vo.DeviceVO;
import com.xbyrh.service.IDeviceService;
import com.xbyrh.web.model.params.DeviceListParam;
import com.xbyrh.repo.model.support.BaseResponse;
import com.xbyrh.repo.model.support.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private DeviceMapper deviceMapper;

    @GetMapping("list")
    public PaginationResponse<DeviceVO> deviceList(DeviceListParam deviceListParam) {
        User user = AuthContext.getUser();
        List<Device> deviceList = deviceService.getDevicesByUid(user.getUid(),
                                                                deviceListParam.getDeviceName(),
                                                                deviceListParam.getDeviceType(),
                                                                deviceListParam.getPage(),
                                                                deviceListParam.getLimit());

        Long total = deviceService.countByUserId(user.getUid(), deviceListParam.getDeviceName(),
                                                 deviceListParam.getDeviceType());
        return PaginationResponse.ok(total, deviceMapper.toVOList(deviceList));
    }

    @PostMapping("save")
    public BaseResponse<String> save(@RequestBody DeviceAddParam deviceAddParam) {
        DeviceBO deviceBO = deviceMapper.toBO(deviceAddParam);
        deviceService.save(deviceBO);
        return BaseResponse.ok();
    }

    @PostMapping("delete")
    public BaseResponse<String> delete(@RequestBody DeviceDeleteParam deviceDeleteParam) {
        deviceService.delete(deviceDeleteParam.getId());
        return BaseResponse.ok();
    }


    @PostMapping("delete/status")
    public BaseResponse<String> updateDeleteStatus(@RequestBody DeviceDeleteParam deviceDeleteParam) {
        deviceService.updateDeleteStatus(deviceDeleteParam.getId());
        return BaseResponse.ok();
    }
}
