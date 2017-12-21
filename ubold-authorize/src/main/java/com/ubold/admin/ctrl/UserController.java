package com.ubold.admin.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.response.Response;
import com.ubold.admin.vo.IndexParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by ningzuokun on 2017/11/22.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = PermitPrefixURI.permit + "/index", method = RequestMethod.POST)
    public Response index(@RequestBody IndexParam indexParam) {
        logger.info("rabc::UserController::index::{}", JSONObject.toJSONString(indexParam));
        return Response.SUCCESS();
    }

    @RequestMapping("/authority/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}