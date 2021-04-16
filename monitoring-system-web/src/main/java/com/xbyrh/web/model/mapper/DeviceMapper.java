package com.xbyrh.web.model.mapper;

import com.xbyrh.repo.entity.Device;
import com.xbyrh.repo.entity.User;
import com.xbyrh.web.model.dto.DeviceDTO;
import com.xbyrh.web.model.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceDTO toDTO(Device device);

    List<DeviceDTO> toDTOList(List<Device> deviceList);

}
