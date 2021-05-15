package com.xbyrh.repo.model.mapper;

import com.xbyrh.common.enums.DeviceTypeEnum;
import com.xbyrh.repo.entity.Role;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.model.bo.UserBO;
import com.xbyrh.repo.model.bo.UserSaveBO;
import com.xbyrh.repo.model.bo.UserUpdateBO;
import com.xbyrh.repo.model.params.UserSaveParam;
import com.xbyrh.repo.model.params.UserUpdateParam;
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
public interface UserConverter {

    UserVO toVO(User user);

    UserBO toBO(User user);

    List<UserBO> toBOList(List<User> userList);

    UserUpdateBO toBO(UserUpdateParam userUpdateParam);

    UserSaveBO toBO(UserSaveParam userSaveParam);

//    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
//    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
//    UserListVO toVo(User user);
//
//    List<UserListVO> toVoList(List<User> userList);

    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "roleList", target = "roleList", qualifiedByName = "roleListConverter")
    UserListVO toVo(UserBO userBO);

    List<UserListVO> toVoList(List<UserBO> userBOList);

    @Named("roleListConverter")
    static String roleListConverter(List<Role> roleList) {
        return roleList.stream().map(Role::getName).collect(Collectors.joining(","));
    }

}
