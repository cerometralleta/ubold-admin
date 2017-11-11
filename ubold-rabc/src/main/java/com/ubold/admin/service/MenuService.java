package com.ubold.admin.service;

import com.ubold.admin.domain.Menu;
import com.ubold.admin.domain.Permission;

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
}
