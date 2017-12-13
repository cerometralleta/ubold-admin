package com.ubold.admin.ctrl;

import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.domain.User;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.AuthService;
import com.ubold.admin.vo.LoginParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by ningzuokun on 2017/11/22.
 */
@RestController
@RequestMapping("/rabc/auth")
public class AuthController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AuthService authService;
    /**
     * 创建视图
     *
     * @param request
     * @return
     */
    @RequestMapping(value = PermitPrefixURI.api_permit + "/login", method = RequestMethod.POST)
    public Response login(@RequestBody @Valid LoginParam loginParam) {
        return authService.login(loginParam);
    }

    @RequestMapping(value = PermitPrefixURI.api_permit + "/doLogin", method = RequestMethod.GET)
    public Response dologin() {
        return Response.SUCCESS();
    }


    @RequestMapping(value = PermitPrefixURI.api + "/index", method = RequestMethod.GET)
    public Response index() {
        logger.info("AuthController::index");
        return Response.SUCCESS();
    }

    @RequestMapping(value = PermitPrefixURI.api + "/user", method = RequestMethod.POST)
    public Response user() {
        logger.info("AuthController::user");
        return Response.SUCCESS();
    }

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}