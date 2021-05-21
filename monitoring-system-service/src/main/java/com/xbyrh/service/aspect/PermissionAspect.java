package com.xbyrh.service.aspect;

import com.xbyrh.common.annotations.Permission;
import com.xbyrh.common.enums.PermissionEnum;
import com.xbyrh.common.exception.NoPermissionException;
import com.xbyrh.repo.entity.User;
import com.xbyrh.service.IAuthService;
import com.xbyrh.service.context.AuthContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * create at 2021/5/21
 *
 * @author chenxinhui
 */
@Aspect
@Component
public class PermissionAspect {

    @Autowired
    private IAuthService authService;

    @Pointcut("@annotation(com.xbyrh.common.annotations.Permission)")
    public void pointcut(){}

    @Around("pointcut() && @annotation(permission)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, Permission permission) throws Throwable {
        PermissionEnum permissionEnum = permission.value();
        User user = AuthContext.getUser();
        List<String> permissionCodeList = authService.getPermissionCodeList(user.getUid());

        if (!CollectionUtils.isEmpty(permissionCodeList) && !permissionCodeList.contains(permissionEnum.getCode())) {
            throw new NoPermissionException(String.format("用户没有%s的权限", permissionEnum.getDesc()));
        }

        return proceedingJoinPoint.proceed();

    }
}
