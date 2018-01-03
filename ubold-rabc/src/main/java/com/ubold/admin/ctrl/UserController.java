package com.ubold.admin.ctrl;

import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.AuthService;
import com.ubold.admin.vo.JwtRequestParam;
import com.ubold.admin.vo.LoginParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by ningzuokun on 2017/11/22.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AuthService authService;

    @RequestMapping(value = PermitPrefixURI.permit + "/clientLogin", method = RequestMethod.POST)
    public Response clientLogin(@RequestBody @Valid LoginParam loginParam) throws Exception {
        return authService.ClientPasswordLogin(loginParam);
    }

    @RequestMapping(value = PermitPrefixURI.permit + "/index", method = RequestMethod.GET)
    public void index() {
        logger.info("rabc::UserController::index");
    }

    @RequestMapping(value = "/me", method = RequestMethod.POST)
    public Response user(@RequestBody JwtRequestParam param) {
        return authService.getUserInfoByToken(param.getAuthorization());
    }

    @RequestMapping("/principal")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}