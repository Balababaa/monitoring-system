package com.xbyrh.web.model.params;

import com.xbyrh.repo.model.params.PaginationParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceListParam extends PaginationParam {

    private String deviceName;

    private Integer deviceType;

}
