package com.xbyrh.repo.model.mapper;

import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.model.bo.UserUpdateBO;
import com.xbyrh.repo.model.params.UserUpdateParam;
import com.xbyrh.repo.model.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * create at 16:30
 *
 * @author chenxinhui
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserVO toVO(User user);

    UserUpdateBO toBO(UserUpdateParam userUpdateParam);

}
