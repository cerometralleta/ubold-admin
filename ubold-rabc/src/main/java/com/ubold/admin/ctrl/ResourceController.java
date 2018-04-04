package com.ubold.admin.ctrl;

import com.ubold.admin.request.Request;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.ResourceService;
import com.ubold.admin.vo.AuthorizeUrlParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/authorizeUrl", method = RequestMethod.GET)
    public Response authorizeUrl(@RequestBody AuthorizeUrlParam authorizeUrlParam) {
        return resourceService.authorizeUrl(authorizeUrlParam);
    }

    @RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
    public Response getMenuList(@RequestBody Request request) {
        return resourceService.getMenuList(request.getSessionUserId());
    }
}
