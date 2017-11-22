package com.ubold.admin.security;

import com.ubold.admin.domain.JwtUser;
import com.ubold.admin.service.PermissionService;
import com.ubold.admin.service.UserService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 跟 AuthenticationProvider 关系密切，用来获取用户信息的。
 * Created by ningzuokun on 2017/11/10.
 */
@Service
public class UboldUserDetailsService implements UserDetailsService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private UserCache userCache = new NullUserCache();

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("UboldUserDetailsService::loadUserByUsername= {}",username);

        //查询用户是否存在
//        List<User> users = userService.findByUserName(username);
//        if(CollectionUtils.isEmpty(users)){
//            throw new UsernameNotFoundException("username not found.");
//        }
//        User curUser = users.get(0);
        //用户权限
//        List<Permission>  permissions = permissionService.findAllPermissionByUser(curUser);
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
//        List<String> links = permissionService.findAllPermissionLink(permissions);
//        if(CollectionUtils.isNotEmpty(links)){
//            links.forEach(link ->{
//                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ link));//必须ROLE_为前缀
//            });
//        }
//        if(CollectionUtils.isNotEmpty(permissions)){
//            for(Permission permission:permissions){
//                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ permission.getCode()));//必须ROLE_为前缀
//            }
//        }
        //获取用户权限
        logger.info("UboldUserDetailsService::grantedAuthorities = {}", grantedAuthorities);
        JwtUser jwtUser = new JwtUser(username, "$2a$10$bFnRuIzRSOvkxhiwOamacumwypMWcNgEWu5r2DSHG93b9SpubZMmq",
                true, true,
                true, true,grantedAuthorities);
        jwtUser.setAuthToken(jwtTokenUtil.generateToken(jwtUser));
        return jwtUser;
    }

    public UserCache getUserCache() {
        return userCache;
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }
}
