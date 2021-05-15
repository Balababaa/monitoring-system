package com.xbyrh.repo.model.params;

import lombok.Data;

import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Data
public class RoleAddParam {

    private Long uid;

    private List<String> codeList;

}
