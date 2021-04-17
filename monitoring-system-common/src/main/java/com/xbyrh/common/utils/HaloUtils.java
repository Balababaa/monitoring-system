package com.xbyrh.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Common utils
 *
 * @author ryanwang
 * @author johnniang
 * @date 2017-12-22
 */
@Slf4j
public class HaloUtils {

    /**
     * Gets random uuid without dash.
     *
     * @return random uuid without dash
     */
    
    public static String randomUUIDWithoutDash() {
        return StringUtils.remove(UUID.randomUUID().toString(), '-');
    }

}
