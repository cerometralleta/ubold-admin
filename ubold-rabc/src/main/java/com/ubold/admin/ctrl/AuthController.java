package com.ubold.admin.ctrl;

import com.ubold.admin.constant.CtrlConstant;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.AuthService;
import com.ubold.admin.vo.LoginParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ningzuokun on 2017/11/22.
 */
@RestController
@RequestMapping
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
    @ResponseBody
    @RequestMapping(value = CtrlConstant.api_permit + "/rabc/auth/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response login(@RequestBody @Valid LoginParam loginParam) {
        return authService.login(loginParam);
    }


    @ResponseBody
    @RequestMapping(value = CtrlConstant.api + "/rabc/auth/index", method = RequestMethod.GET)
    public Response index() {
        logger.info("AuthController::index");
        return Response.SUCCESS();
    }

}