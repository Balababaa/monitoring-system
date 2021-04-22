package com.xbyrh.repo.model.mapper;

import com.xbyrh.common.enums.DeviceTypeEnum;
import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.model.bo.DeviceBO;
import com.xbyrh.repo.model.params.DeviceAddParam;
import com.xbyrh.repo.model.vo.DeviceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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
    @Mapping(source = "isDelete", target = "isDelete", qualifiedByName = "isDeleteMapper")
    @Mapping(source = "deviceType", target = "deviceType", qualifiedByName = "deviceTypeMapper")
    DeviceVO toVO(Device device);

    List<DeviceVO> toVOList(List<Device> deviceList);

    DeviceBO toBO(DeviceAddParam deviceAddParam);

    @Named("isDeleteMapper")
    static Boolean isDeleteMapper(Integer isDelete) {
        if (isDelete == 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    @Named("deviceType")
    static String deviceTypeMapper(Integer deviceType) {
        return DeviceTypeEnum.getByCode(deviceType).getDesc();
    }

}
