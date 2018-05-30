package com.ubold.admin.ctrl;

import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.model.AccountInfoModel;
import com.ubold.admin.request.Request;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.ResourceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by ningzuokun on 2017/11/22.
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    ResourceService resourceService;

    @ApiOperation(value = "首页",notes = "应用程序首页")
    @RequestMapping(value = PermitPrefixURI.permit + "/index", method = RequestMethod.GET)
    public Response index() {
        return Response.SUCCESS("Service started ...");
    }


    @ApiOperation(value = "principal",notes = "principal")
    @RequestMapping(value = "/principal",method= RequestMethod.POST)
    public Principal user(Principal principal) {
        log.info("principal",principal);
        return principal;
    }

    @ApiOperation(value = "获取账号基本信息")
    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.GET)
    public Response<AccountInfoModel> getAccount(Request request) {
        return Response.SUCCESS(resourceService.getAccountInfo(request.getSessionUserId()));
    }
}