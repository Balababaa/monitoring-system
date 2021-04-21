package com.xbyrh.repo.model.params;

import lombok.Data;

/**
 * create at 2021/4/17
 *
 * @author chenxinhui
 */
@Data
public class PaginationParam {

    private Integer page = 1;

    private Integer limit = 10;

}
