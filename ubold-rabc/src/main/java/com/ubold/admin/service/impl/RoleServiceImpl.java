package com.ubold.admin.service.impl;


import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2017/11/11.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Response findPermissionByUser(UserInfo user) {
        if(null == user){
            return Response.FAILURE();
        }
        //获取用户拥有的所有角色



        return null;
    }
}
