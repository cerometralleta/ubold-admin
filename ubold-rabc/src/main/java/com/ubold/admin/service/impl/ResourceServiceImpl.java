package com.ubold.admin.service.impl;

import com.ubold.admin.constants.MenuTypeEnum;
import com.ubold.admin.domain.Resource;
import com.ubold.admin.domain.ResourceInfo;
import com.ubold.admin.model.AuthorizeUrlParam;
import com.ubold.admin.model.GetMenuResult;
import com.ubold.admin.repository.ResourceRepository;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.ResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/11/11.
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    private static final String TOP_NODEID = "0";

    @Autowired
    ResourceRepository resourceRepository;

    //TODO 缓存
    @Override
    public List<Resource> findAllResourceByUserId(String userId) {
        // 根据用户查询角色关联的所有资源
        return resourceRepository.findResourceByRoleUserId(userId);
    }

    @Override
    public List<String> findAllResourceLink(List<Resource> resources) {
        List<String> resourceLinkList = Lists.newArrayList();
        //查询菜单
        for (ResourceInfo resource : resources) {
            resourceLinkList.add(resource.getLink());
        }
        return resourceLinkList;
    }

    @Override
    public Response authorizeUrl(AuthorizeUrlParam authorizeUrlParam) {
        List<Resource> resources = this.findAllResourceByUserId(authorizeUrlParam.getSessionUserId());
        List<String> resourceLinkList = this.findAllResourceLink(resources);
        if (resourceLinkList.contains(authorizeUrlParam.getUrl())) {
            Response.SUCCESS();
        }
        return Response.FAILURE();
    }

    @Override
    public Response<GetMenuResult> getMenuList(String userId) {
        GetMenuResult getMenuResult = new GetMenuResult();
        List<Resource> resources = resourceRepository
                .findResourceByRoleUserIdAndType(userId, MenuTypeEnum.MENU.getCode());
        if (CollectionUtils.isEmpty(resources)) {
            return Response.FAILURE();
        }
        List<ResourceInfo> parentList = Lists.newArrayList();
        getMenuResult.setResources(parentList);
        //字节集合,key为parent
        Map<String, List<ResourceInfo>> childMap = new HashedMap();
        for (ResourceInfo resource : resources) {

            //一级节点
            if (TOP_NODEID.equals(resource.getParent())) {
                parentList.add(resource);
                continue;
            }

            //子节点集合
            if (childMap.containsKey(resource.getParent())) {
                childMap.get(resource.getParent()).add(resource);
            } else {
                List<ResourceInfo> childList = Lists.newArrayList();
                childList.add(resource);
                childMap.put(resource.getParent(), childList);
            }
        }
        for (ResourceInfo resource : parentList) {
            loopGetMenuList(resource, childMap);
        }
        return Response.SUCCESS(getMenuResult);
    }

    //TODO cache..
    @Override
    public Map<String, String> getAuthority(String userId) {
        Map<String, String> authorityMap = new HashedMap();
        List<Resource> resources = this.findAllResourceByUserId(userId);
        if (CollectionUtils.isEmpty(resources)) {
            return authorityMap;
        }
        for (ResourceInfo resource : resources) {
            if (StringUtils.isNotBlank(resource.getLink())) {
                authorityMap.put(resource.getLink(), resource.getLink());
            }
        }
        return authorityMap;
    }

    //遍历
    protected void loopGetMenuList(ResourceInfo resource, Map<String, List<ResourceInfo>>
            childMap) {

        //当前节点存在子节点
        if (!childMap.containsKey(resource.getId())) {
            return;
        }
        resource.setChilds(childMap.get(resource.getId()));
        for (ResourceInfo res : resource.getChilds()) {
            loopGetMenuList(res, childMap);
        }
    }
}
