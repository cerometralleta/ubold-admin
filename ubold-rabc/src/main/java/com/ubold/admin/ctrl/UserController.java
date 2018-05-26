package com.ubold.admin.ctrl;

import com.ubold.admin.constant.PermitPrefixURI;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ApiOperation(value = "首页",notes = "应用程序首页")
    @RequestMapping(value = PermitPrefixURI.permit + "/index", method = RequestMethod.GET)
    public void index() {
        log.info("rabc::UserController::index");
    }


    @ApiOperation(value = "principal",notes = "principal")
    @RequestMapping(value = "/principal",method= RequestMethod.POST)
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}