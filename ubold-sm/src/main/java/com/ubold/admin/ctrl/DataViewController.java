package com.ubold.admin.ctrl;

import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import com.ubold.admin.service.SqlIdJdbcService;
import com.ubold.admin.vo.QuerytableParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 视图服务
 * Created by lenovo on 2017/8/13.
 */
@Slf4j
@RestController
@RequestMapping("/sm/view")
public class DataViewController{

    @Autowired
    DataViewService dataViewService;
    @Autowired
    SqlIdJdbcService sqlIdJdbcService;
    /**
     * 创建视图
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value= "/persistent",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response persistent(@RequestBody @Valid  DataViewCreateRequest request) {
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
        log.info("application started");
        return Response.SUCCESS("ubold started");
    }

    @ResponseBody
    @RequestMapping(value="/queryTableschemas")
    public Response queryTableschema(@RequestBody @Valid QuerytableParam querytableParam) {
        return Response.SUCCESS(sqlIdJdbcService.queryTableschema(querytableParam));
    }

    @ResponseBody
    @RequestMapping(value="/queryTableschemaInfo")
    public Response queryTableschemaInfo(@RequestBody @Valid QuerytableParam querytableParam) {
        return dataViewService.queryTableschemaInfo(querytableParam);
    }
}
