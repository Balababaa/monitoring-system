package com.xbyrh.repo.model.support;

import com.xbyrh.common.enums.ResponseEnum;

import java.util.List;

/**
 * create at 2021/4/17
 *
 * @author chenxinhui
 */
public class PaginationResponse<T> extends BaseResponse<Pagination<T>> {

    public PaginationResponse(ResponseEnum responseEnum, Pagination<T> data) {
        super(responseEnum, data);
    }

    public static <T> PaginationResponse<T> ok(Long total, List<T> data) {
        Pagination<T> pagination = Pagination.of(total, data);
        return new PaginationResponse<>(ResponseEnum.SUCCESS, pagination);
    }

}
