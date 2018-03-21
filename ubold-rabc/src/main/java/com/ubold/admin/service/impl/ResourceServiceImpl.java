package com.ubold.admin.service.impl;

import com.ubold.admin.domain.Resource;
import com.ubold.admin.repository.ResourceRepository;
import com.ubold.admin.service.ResourceService;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ResourceRepository resourceRepository;

    //TODO 缓存
    @Override
    public List<Resource> findAllResourceByUserId(String userId) {
        List<Resource> resourcesList = Lists.newArrayList();
        // 根据用户查询角色关联的所有资源
        List<Resource> resources = resourceRepository.findResourceByRoleUserId(userId);
        if (CollectionUtils.isNotEmpty(resources)) {
            resourcesList.addAll(resources);
        }
        return resourcesList;
    }

    @Override
    public List<String> findAllResourceLink(List<Resource> resources) {
        List<String> resourceLinkList = Lists.newArrayList();
        //查询菜单
        for (Resource resource : resources) {
            resourceLinkList.add(resource.getLink());
        }
        return resourceLinkList;
    }
}
