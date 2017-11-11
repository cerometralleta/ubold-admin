package com.ubold.admin.service;

import com.ubold.admin.domain.Menu;
import com.ubold.admin.domain.Permission;
import com.ubold.admin.domain.User;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
public interface PermissionService {

    /**
     * 根据用户获取所有权限
     * @param user
     * @return
     */
    List<Permission> findAllPermissionByUser(User user);

    /**
     * 获取所有权限link
     * @param permissions
     * @return
     */
    List<String> findAllPermissionLink(List<Permission> permissions);
}
