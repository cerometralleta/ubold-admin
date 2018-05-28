package com.ubold.admin.service;

import com.ubold.admin.domain.Resources;
import com.ubold.admin.model.AuthorizeUrlParam;
import com.ubold.admin.model.GetMenuResult;
import com.ubold.admin.response.Response;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/11/11.
 */
public interface ResourceService {

    /**
     * 根据用户获取所有权限
     * @param user
     * @return
     */
    List<Resources> findAllResourceByUserId(String userId);

    /**
     * 获取所有权限link
     * @param permissions
     * @return
     */
    List<String> findAllResourceLink(List<Resources> resources);
    Response authorizeUrl(AuthorizeUrlParam authorizeUrlParam);

    Response<GetMenuResult> getMenuItems(String userId);

    Map<String, String> getAuthority(String userId);
}
