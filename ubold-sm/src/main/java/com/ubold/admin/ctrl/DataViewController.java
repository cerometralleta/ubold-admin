package com.ubold.admin.ctrl;

import com.alibaba.fastjson.JSON;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import net.minidev.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 视图服务
 * Created by lenovo on 2017/8/13.
 */
@RestController
@RequestMapping("/api/permit/sm")
public class DataViewController{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataViewService dataViewService;
    /**
     * 创建视图
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/create",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response create(@RequestBody DataViewCreateRequest request) {
        logger.info(JSON.toJSONString(request));
        return dataViewService.create(request);
    }

    @ResponseBody
    @RequestMapping(value="/index")
    public Response index() {
        logger.info("application started");
        return Response.SUCCESS("ubold started");
    }
}
