package com.ubold.admin.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.SqlDefineService;
import com.ubold.admin.vo.ColumnVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lenovo on 2017/8/30.
 */
@RestController
@RequestMapping("/api/sqlDefine")
public class SqlDefineController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SqlDefineService sqlDefineService;

    @ResponseBody
    @RequestMapping(value="/persistent",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response persistent(@RequestBody @Valid JSONObject paramJson) {
        return sqlDefineService.persistent(paramJson);
    }

    @ResponseBody
    @RequestMapping(value="/createColumnList/{sqlId}",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response createColumnList(@PathVariable String sqlId) {
        List<ColumnVo> list = sqlDefineService.getColumnsBySqlId(sqlId);
        return Response.SUCCESS(list);
    }

}
