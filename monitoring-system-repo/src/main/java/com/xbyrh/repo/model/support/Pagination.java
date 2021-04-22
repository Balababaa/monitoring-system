package com.xbyrh.repo.model.support;

import lombok.Data;

import java.util.List;

/**
 * create at 2021/4/17
 *
 * @author chenxinhui
 */
@Data
public class Pagination<T> {

    public Pagination(Long total, List<T> dataList) {
        this.total = total;
        this.dataList = dataList;
    }

    private Long total;

    private List<T> dataList;

    public static  <T> Pagination<T> of(Long total, List<T> dataList){
        return new Pagination<>(total, dataList);
    }

}
