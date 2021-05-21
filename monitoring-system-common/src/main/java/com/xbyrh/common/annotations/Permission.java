package com.xbyrh.common.annotations;

import com.xbyrh.common.enums.PermissionEnum;

import java.lang.annotation.*;

/**
 * create at 2021/5/21
 *
 * @author chenxinhui
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Permission {

    PermissionEnum value();

}
