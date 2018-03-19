package com.ubold.admin.aop;

import com.ubold.admin.request.Request;
import com.ubold.admin.vo.SessionInfo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 功能过滤
 * Created by ningzuokun on 2017/12/21.
 */
@Aspect
@Component
public class UboldAuthorityAspect {

    // 用@Pointcut来注解一个切入方法
    @Pointcut("execution(* com.ubold.admin.ctrl.*.**(..))")
    public void pointcutCtrl() {
    }

    @Around(value = "pointcutCtrl()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if (null == args) {
            return point.proceed();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (Object parameterAgrs : args) {
            if (parameterAgrs instanceof Request) {
                ((Request) parameterAgrs).setSessionUserId(((SessionInfo) authentication.getDetails()).getUserId());
            }
        }
        return point.proceed();
    }
}
