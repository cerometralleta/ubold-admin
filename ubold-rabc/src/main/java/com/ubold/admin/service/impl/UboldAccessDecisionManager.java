package com.ubold.admin.service.impl;

import com.ubold.admin.domain.Role;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * 为 Web 或方法的安全提供访问决策。
 * 会注册一个默认的，但是我们也可以通过普通 bean 注册的方式使用自定义的 AccessDecisionManager。
 * Created by lenovo on 2017/11/10.
 */
@Component
public class UboldAccessDecisionManager implements AccessDecisionManager {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private static final String ADMINISTRATOR = "administrator";
    /**
     * 通过传递的参数来决定用户是否有访问对应受保护对象的权限
     *
     * @param authentication 当前正在请求受包含对象的Authentication
     * @param object 受保护对象，其可以是一个MethodInvocation、JoinPoint或FilterInvocation。
     * @param collection 与正在请求的受保护对象相关联的配置属性
     *
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //获取用户所有权限
        Collection<GrantedAuthority> userHasRoles = (Collection<GrantedAuthority>) authentication.getAuthorities();
        logger.info("UboldAccessDecisionManager::decide::CurrentUser={} CurrentHasRoles = {}", authentication.getName(), Arrays.asList(userHasRoles));
        //放行[超级管理员]角色
        Iterator<GrantedAuthority> iterator = userHasRoles.iterator();
        while (iterator.hasNext()){
            GrantedAuthority grantedAuthority = iterator.next();
            if(this.ADMINISTRATOR.equals(grantedAuthority.getAuthority())){
                return;
            }
        }
        Collection<GrantedAuthority> uriHasRoles = getGrantedAuthoritys(object);
//        if (CollectionUtils.isNotEmpty(uriHasRoles)) {
        if(true){
            return;
        }
        throw new AccessDeniedException("Access Denied.");
    }

    private Collection<GrantedAuthority> getGrantedAuthoritys(Object object) {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String uri = new StringBuilder(filterInvocation.getRequestUrl()).deleteCharAt(0).toString();
        if("".equals(uri)){
            return null;
        }
//        List<Role> uriHasRoles = roleService.selectByResourceURI(uri);
        logger.info("fullRequestUrl={}, requestUrl={}, uriHasRoles={}",
                filterInvocation.getFullRequestUrl(),
                filterInvocation.getRequestUrl(),
                null);
//        if (uriHasRoles == null || uriHasRoles.size() == 0) {
//            return null;
//        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        uriHasRoles.forEach(item -> {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(item.getName());
//            grantedAuthorities.add(grantedAuthority);
//        });
        return grantedAuthorities;
    }

    /**
     * 表示当前AccessDecisionManager是否支持对应的ConfigAttribute
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    /**
     * 表示当前AccessDecisionManager是否支持对应的受保护对象类型
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
