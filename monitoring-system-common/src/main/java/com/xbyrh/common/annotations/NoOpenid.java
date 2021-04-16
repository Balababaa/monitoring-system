package com.xbyrh.common.annotations;

import java.lang.annotation.*;

/**
 * create at 16:44
 *
 * @author chenxinhui
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NoOpenid {
}
