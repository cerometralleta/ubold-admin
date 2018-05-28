package com.ubold.admin.service;

import com.ubold.admin.domain.Resource;
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
    List<Resource> findAllResourceByUserId(String userId);

    /**
     * 获取所有权限link
     * @param permissions
     * @return
     */
    List<String> findAllResourceLink(List<Resource> resources);
    Response authorizeUrl(AuthorizeUrlParam authorizeUrlParam);

    Response<GetMenuResult> getMenuList(String userId);

    Map<String, String> getAuthority(String userId);
}
