package com.xbyrh.repo.model.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * create at 2021/5/13
 *
 * @author chenxinhui
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserListParam extends PaginationParam{

    private String username;

}
