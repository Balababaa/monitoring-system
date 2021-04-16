package com.xbyrh.web.model.mapper;

import com.xbyrh.repo.entity.User;
import com.xbyrh.web.model.dto.UserDTO;
import com.xbyrh.web.model.params.UserInfoParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * create at 16:30
 *
 * @author chenxinhui
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User fromParam(UserInfoParam userInfoParam);

}
