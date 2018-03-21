package com.ubold.admin.service;

import com.ubold.admin.domain.Resource;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
public interface ResourceService {

    /**
     * 根据用户获取所有权限
     * @param user
     * @return
     */
    List<Resource> findAllResourceByUserId(String userId);

    /**
     * 获取所有权限link
     * @param permissions
     * @return
     */
    List<String> findAllResourceLink(List<Resource> resources);
}
