package com.ubold.admin.security;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.response.Response;
import com.ubold.admin.util.ValidationUtil;
import com.ubold.admin.vo.ClaimsResult;
import com.ubold.admin.vo.JwtRequestParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ningzuokun on 2017/12/21.
 */
@Aspect
@Configuration
public class JwtRequestAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    // 定义切点Pointcut
    @Pointcut("execution(* com.ubold.admin.ctrl..*.*(..))")
    public void endpoints() {
    }

    @Around("endpoints()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Object[] args = proceedingJoinPoint.getArgs();
        for(Object arg : args){
            if(arg instanceof JwtRequestParam){
                JwtRequestParam jwtRequestParam = (JwtRequestParam)arg;
                String token = request.getHeader("Authorization");
                jwtRequestParam.setAuthorization(token);
                Response checkResp = ValidationUtil.validator(jwtRequestParam);
                if(!checkResp.checkSuccess()){
                    return checkResp;
                }
                jwtRequestParam.setAuthorization(jwtRequestParam.getAuthorization().replace("Bearer ",""));
                Jwt jwt = JwtHelper.decode(jwtRequestParam.getAuthorization());
                ClaimsResult claimsParam = JSONObject.parseObject(jwt.getClaims(),ClaimsResult.class);
                jwtRequestParam.setUsername(claimsParam.getUser_name());
            }
        }
        Object response = proceedingJoinPoint.proceed(args);
        return response;
    }
}
