package com.ubold.admin.service;

import com.ubold.admin.domain.User;
import com.ubold.admin.response.Response;

/**
 * Created by lenovo on 2017/11/11.
 */
public interface RoleService {

    /**
     * 获取角色拥有所有权限link
     * @param user
     * @return
     */
    public Response findPermissionByUser(User user);
}
