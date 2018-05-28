package com.ubold.admin.ctrl;

import com.ubold.admin.model.AuthorizeUrlParam;
import com.ubold.admin.model.GetMenuResult;
import com.ubold.admin.request.Request;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.ResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ningzuokun on 2018/3/22.
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @ApiOperation(value = "URL鉴权")
    @RequestMapping(value = "/authorizeUrl", method = RequestMethod.GET)
    public Response authorizeUrl(@RequestBody AuthorizeUrlParam authorizeUrlParam) {
        return resourceService.authorizeUrl(authorizeUrlParam);
    }

    @ApiOperation(value = "获取授权菜单列表")
    @RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
    public Response<GetMenuResult> getMenuList(Request request) {
        return resourceService.getMenuList(request.getSessionUserId());
    }
}
