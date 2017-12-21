package com.ubold.admin.service;

import com.ubold.admin.domain.Menu;
import com.ubold.admin.domain.Permission;
import com.ubold.admin.response.Response;
import com.ubold.admin.vo.AuthorizeResult;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
public interface MenuService {

    /**
     * 根据权限获取菜单
     * @param permissions
     * @return
     */
    List<Menu> findMenuByPermission(String permissions);


    /**
     * 根据用户id获取授权url
     * @param userId
     * @return
     */
    Response<AuthorizeResult> getAuthorizeUrl(String userId);
}
