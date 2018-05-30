package com.ubold.admin.service.impl;

import com.ubold.admin.constants.MenuTypeEnum;
import com.ubold.admin.domain.Resources;
import com.ubold.admin.domain.User;
import com.ubold.admin.model.AccountInfoModel;
import com.ubold.admin.model.AuthorizeUrlParam;
import com.ubold.admin.model.GetMenuModel;
import com.ubold.admin.repository.ResourceRepository;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.ResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<Resources> findAllResourceByUserId(String userId) {
        // 根据用户查询角色关联的所有资源
        return resourceRepository.findResourceByRoleUserId(userId);
    }

    @Override
    public List<String> findAllResourceLink(List<Resources> resources) {
        List<String> resourceLinkList = Lists.newArrayList();
        //查询菜单
        for (Resources resource : resources) {
            resourceLinkList.add(resource.getLink());
        }
        return resourceLinkList;
    }

    @Override
    public Response authorizeUrl(AuthorizeUrlParam authorizeUrlParam) {
        List<Resources> resources = this.findAllResourceByUserId(authorizeUrlParam.getSessionUserId());
        List<String> resourceLinkList = this.findAllResourceLink(resources);
        if (resourceLinkList.contains(authorizeUrlParam.getUrl())) {
            Response.SUCCESS();
        }
        return Response.FAILURE();
    }

    @Override
    public Response<GetMenuModel> getMenuItems(String userId) {
        GetMenuModel getMenuResult = new GetMenuModel();
        List<Resources> resources = resourceRepository
                .findResourceByRoleUserIdAndType(userId, MenuTypeEnum.MENU.getCode());
        if (CollectionUtils.isEmpty(resources)) {
            return Response.FAILURE();
        }
        List<Resources> parentList = Lists.newArrayList();
        getMenuResult.setResources(parentList);
        //字节集合,key为parent
        Map<String, List<Resources>> childMap = new HashedMap();
        for (Resources resource : resources) {

            //一级节点
            if (TOP_NODEID.equals(resource.getParent())) {
                parentList.add(resource);
                continue;
            }

            //子节点集合
            if (childMap.containsKey(resource.getParent())) {
                childMap.get(resource.getParent()).add(resource);
            } else {
                List<Resources> childList = Lists.newArrayList();
                childList.add(resource);
                childMap.put(resource.getParent(), childList);
            }
        }
        for (Resources resource : parentList) {
            loopGetMenuItems(resource, childMap);
        }
        return Response.SUCCESS(getMenuResult);
    }

    //TODO cache..
    @Override
    public Map<String, String> getAuthority(String userId) {
        Map<String, String> authorityMap = new HashedMap();
        List<Resources> resources = this.findAllResourceByUserId(userId);
        if (CollectionUtils.isEmpty(resources)) {
            return authorityMap;
        }
        for (Resources resource : resources) {
            if (StringUtils.isNotBlank(resource.getLink())) {
                authorityMap.put(resource.getLink(), resource.getLink());
            }
        }
        return authorityMap;
    }

    @Override
    public AccountInfoModel getAccountInfo(String userId) {
        AccountInfoModel accountInfoModel = new AccountInfoModel();

        //读取用户信息
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
        accountInfoModel.setUser(user);

        //登录返回菜单列表
        Response<GetMenuModel> resultResponse = getMenuItems(userId);
        accountInfoModel.setMenu(resultResponse.getResult().getResources());

        //aPP配置
        AccountInfoModel.App app = new AccountInfoModel.App();
        app.setName("Ubold");
        app.setDescription("Ng-zorro admin panel front-end framework");
        accountInfoModel.setApp(app);
        return accountInfoModel;
    }

    //遍历
    protected void loopGetMenuItems(Resources resource, Map<String, List<Resources>>
            childMap) {

        //当前节点存在子节点
        if (!childMap.containsKey(resource.getId())) {
            return;
        }
        resource.setChildren(childMap.get(resource.getId()));
        for (Resources res : resource.getChildren()) {
            loopGetMenuItems(res, childMap);
        }
    }
}
