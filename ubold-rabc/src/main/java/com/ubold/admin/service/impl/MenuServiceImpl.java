package com.ubold.admin.service.impl;

import com.ubold.admin.domain.Menu;
import com.ubold.admin.domain.Permission;
import com.ubold.admin.repository.MenuRepository;
import com.ubold.admin.service.MenuService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public List<Menu> findMenuByPermission(String permissions) {
        if(StringUtils.isEmpty(permissions)){
            return null;
        }
        return menuRepository.findMenuByPermissions(permissions.toString());
    }
}
