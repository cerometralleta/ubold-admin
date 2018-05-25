package com.ubold.admin.ctrl;

import com.alibaba.fastjson.JSON;
import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.request.FormViewRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.FormViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 视图服务
 * Created by lenovo on 2017/8/13.
 */
@RestController
@RequestMapping("/sm/form")
//@RequestMapping("/api/permit/sm")
public class FormViewController {

    @Autowired
    FormViewService formViewService;
    /**
     * 创建视图
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/persistent",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response persistent(@RequestBody @Valid FormViewRequest request) {
        return formViewService.persistent(request);
    }

    /**
     * 根据视图编号查询
     * @param dataViewCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/find/{code}")
    public Response findByDataViewCode(@PathVariable String code) {
        return formViewService.findByCode(code);
    }
}
