package com.xbyrh.repo.model.mapper;

import com.xbyrh.common.enums.DeviceTypeEnum;
import com.xbyrh.common.enums.PermissionTypeEnum;
import com.xbyrh.repo.entity.Permission;
import com.xbyrh.repo.entity.Role;
import com.xbyrh.repo.model.bo.PermissionSaveBO;
import com.xbyrh.repo.model.bo.RoleSaveBO;
import com.xbyrh.repo.model.params.PermissionSaveParam;
import com.xbyrh.repo.model.params.RoleSaveParam;
import com.xbyrh.repo.model.vo.PermissionListVO;
import com.xbyrh.repo.model.vo.RoleListVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * create at 16:30
 *
 * @author chenxinhui
 */
@Mapper(componentModel = "spring")
public interface PermissionConverter {
//
//    UserVO toVO(User user);
//
//    UserUpdateBO toBO(UserUpdateParam userUpdateParam);
//
    PermissionSaveBO toBO(PermissionSaveParam permissionSaveParam);

    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "type", target = "type", qualifiedByName = "typeConverter")
    PermissionListVO toVo(Permission permission);

    List<PermissionListVO> toVoList(List<Permission> permissionList);

    @Named("typeConverter")
    static String typeConverter(Integer type) {
        return PermissionTypeEnum.getByCode(type).getDesc();
    }

}
