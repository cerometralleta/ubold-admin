package com.ubold.admin.ctrl;

import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.model.FindTablesParam;
import com.ubold.admin.model.FindTablesResult;
import com.ubold.admin.model.SqldefinePreviewParam;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import com.ubold.admin.service.SqlIdJdbcService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @RequestMapping(value="/find/{dataViewCode}",method= RequestMethod.POST)
    public Response findByDataViewCode(@ApiParam(value = "视图编码") @PathVariable String dataViewCode) {
        return dataViewService.findByDataViewCode(dataViewCode);
    }

    @ResponseBody
    @RequestMapping(value=PermitPrefixURI.permit + "/index",method= RequestMethod.GET)
    public Response index() {
        return Response.SUCCESS("ubold started");
    }

    @ApiOperation(value = "查询系统表")
    @ResponseBody
    @RequestMapping(value="/queryTableschemas",method= RequestMethod.POST)
    public Response<FindTablesResult> queryTableschemas(@RequestBody @Valid FindTablesParam findTablesParam) {
        return Response.SUCCESS(sqlIdJdbcService.findTables(findTablesParam));
    }

    @ApiOperation(value = "sql定义预览")
    @ResponseBody
    @RequestMapping(value="/queryTableschemaInfo",method= RequestMethod.POST)
    public Response queryTableschemaInfo(@RequestBody @Valid SqldefinePreviewParam sqldefinePreviewParam) {
        return dataViewService.sqldefinePreview(sqldefinePreviewParam);
    }
}
