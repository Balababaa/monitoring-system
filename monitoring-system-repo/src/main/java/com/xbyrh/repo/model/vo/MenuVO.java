package com.xbyrh.repo.model.vo;

import lombok.Data;

import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Data
public class MenuVO {

    private Long menuId;

    private String menuname;

    private String url;

    private List<MenuVO> menus;

}
