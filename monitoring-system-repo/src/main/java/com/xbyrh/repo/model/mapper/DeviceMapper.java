package com.xbyrh.repo.model.mapper;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.model.bo.DeviceBO;
import com.xbyrh.repo.model.params.DeviceAddParam;
import com.xbyrh.repo.model.vo.DeviceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * create at 2021/4/17
 *
 * @author chenxinhui
 */
@Mapper(componentModel = "spring")
public interface DeviceMapper {

    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    DeviceVO toVO(Device device);

    List<DeviceVO> toVOList(List<Device> deviceList);

    DeviceBO toBO(DeviceAddParam deviceAddParam);

}
