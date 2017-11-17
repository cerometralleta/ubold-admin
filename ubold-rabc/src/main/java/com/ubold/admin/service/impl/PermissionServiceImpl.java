package com.ubold.admin.service.impl;

import com.ubold.admin.domain.Functional;
import com.ubold.admin.domain.Menu;
import com.ubold.admin.domain.Permission;
import com.ubold.admin.domain.User;
import com.ubold.admin.repository.PermissionRepository;
import com.ubold.admin.service.FunctionalService;
import com.ubold.admin.service.MenuService;
import com.ubold.admin.service.PermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    MenuService menuService;

    @Autowired
    FunctionalService functionalService;

    @Override
    public List<Permission> findAllPermissionByUser(User user) {
        List<Permission> permissionAll = Lists.newArrayList();
        // 根据用户查询角色关联的所有权限
        List<Permission> permissions = permissionRepository.findPermissionByRoleUserId(user.getId());
        if(CollectionUtils.isNotEmpty(permissions)) {
            permissionAll.addAll(permissions);
        }
        //根据用户查询所有关联权限
        List<Permission> permissions1 = permissionRepository.findPermissionByUserId(user.getId());
        if(CollectionUtils.isNotEmpty(permissions1)){
             permissionAll.addAll(permissions1);
        }
        return  permissionAll;
    }

    @Override
    public List<String> findAllPermissionLink(List<Permission> permissions) {
        StringBuffer permissionBuffer = new StringBuffer();
        for(Permission permission : permissions){
            permissionBuffer.append("'").append(permission.getVersion()).append("'").append(",");
        }
        String permissionIds = permissionBuffer.deleteCharAt(permissionBuffer.lastIndexOf(",")).toString();
        List<String> permitList = Lists.newArrayList();
        //查询菜单
        List<Menu> menus = menuService.findMenuByPermission(permissionIds);
        if(CollectionUtils.isNotEmpty(menus)){
            for(Menu menu : menus){
                permitList.add(menu.getLink());
            }
        }
        //查询功能
        List<Functional> functionals = functionalService.findFunctionalByPermissions(permissionIds);
        if(CollectionUtils.isNotEmpty(menus)){
            for(Functional functional : functionals){
                permitList.add(functional.getLink());
            }
        }
        return permitList;
    }
}