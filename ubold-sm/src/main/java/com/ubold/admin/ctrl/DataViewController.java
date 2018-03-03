package com.ubold.admin.ctrl;

import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import com.ubold.admin.vo.QueryTableInfoParam;
import com.ubold.admin.vo.QuerytableParam;
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
@RequestMapping("/sm/view")
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
    @RequestMapping(value= "/persistent",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response persistent(@RequestBody @Valid  DataViewCreateRequest request) {
//        logger.info(JSON.toJSONString(request));
        return dataViewService.persistent(request);
    }

    /**
     * 根据视图编号查询
     * @param dataViewCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/find/{dataViewCode}")
    public Response findByDataViewCode(@PathVariable String dataViewCode) {
        return dataViewService.findByDataViewCode(dataViewCode);
    }



    @ResponseBody
    @RequestMapping(value=PermitPrefixURI.permit + "/index")
    public Response index() {
        logger.info("application started");
        return Response.SUCCESS("ubold started");
    }

    @ResponseBody
    @RequestMapping(value="/querytables")
    public Response querytable(@RequestBody @Valid QuerytableParam querytableParam) {
        return dataViewService.queryTables(querytableParam);
    }

    @ResponseBody
    @RequestMapping(value="/querytableInfo")
    public Response querytableInfo(@RequestBody @Valid QueryTableInfoParam queryTableInfoParam) {
        return dataViewService.querytableInfo(queryTableInfoParam);
    }
}
