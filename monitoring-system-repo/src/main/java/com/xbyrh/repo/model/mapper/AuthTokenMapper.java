package com.xbyrh.repo.model.mapper;

import com.xbyrh.repo.model.bo.AuthTokenBO;
import com.xbyrh.repo.model.vo.AuthTokenVO;
import org.mapstruct.Mapper;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
@Mapper(componentModel = "spring")
public interface AuthTokenMapper {

    AuthTokenVO toVO(AuthTokenBO authTokenBO);

}
