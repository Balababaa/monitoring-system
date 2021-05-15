package com.xbyrh.repo.model.mapper;

import com.xbyrh.repo.entity.Permission;
import com.xbyrh.repo.entity.Role;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.model.bo.RoleBO;
import com.xbyrh.repo.model.bo.RoleSaveBO;
import com.xbyrh.repo.model.bo.UserSaveBO;
import com.xbyrh.repo.model.bo.UserUpdateBO;
import com.xbyrh.repo.model.params.RoleSaveParam;
import com.xbyrh.repo.model.params.UserSaveParam;
import com.xbyrh.repo.model.params.UserUpdateParam;
import com.xbyrh.repo.model.vo.RoleListVO;
import com.xbyrh.repo.model.vo.UserListVO;
import com.xbyrh.repo.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

/**
 * create at 16:30
 *
 * @author chenxinhui
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {
    //
//    UserVO toVO(User user);
//
//    UserUpdateBO toBO(UserUpdateParam userUpdateParam);
//
    RoleSaveBO toBO(RoleSaveParam roleSaveParam);

    RoleBO toBO(Role role);

    List<RoleBO> toBOList(List<Role> roleList);

    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "permissionList", target = "permissionList", qualifiedByName = "permissionListConverter")
    RoleListVO toVo(RoleBO roleBO);

    List<RoleListVO> toVoList(List<RoleBO> roleBOList);

    @Named("permissionList")
    static String permissionList(List<Permission> permissionList) {
        return permissionList.stream().map(Permission::getName).collect(Collectors.joining(","));
    }

}
