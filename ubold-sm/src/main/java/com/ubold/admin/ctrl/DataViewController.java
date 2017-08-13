package com.ubold.admin.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 视图服务
 * Created by lenovo on 2017/8/13.
 */
@Controller
@RequestMapping("/sm")
public class DataViewController{

    /**
     * 创建视图
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/create",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response create(@RequestBody DataViewCreateRequest request) {

        return Response.SUCCESS();
    }


}
