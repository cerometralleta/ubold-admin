package com.ubold.admin.ctrl;

import com.ubold.admin.response.Response;
import com.ubold.admin.vo.LoginParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lenovo on 2017/11/11.
 */
@RestController
public class LoginController {

    @ResponseBody
    @RequestMapping(value="/login",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response persistent(@RequestBody @Valid LoginParam request) {
        return Response.SUCCESS();
    }
}
