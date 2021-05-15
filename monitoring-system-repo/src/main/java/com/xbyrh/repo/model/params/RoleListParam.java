package com.xbyrh.repo.model.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleListParam extends PaginationParam{

    private String code;

}
