package com.ubold.admin.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.SqlDefineService;
import com.ubold.admin.vo.ColumnVo;
import com.ubold.admin.vo.PageResultForBootstrap;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
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

    @Autowired
    HttpServletRequest httpServletRequest;

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

    /**
     * dataViewCode  根据SQLID返回bootstrapTable数据格式
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/tableData/{sqlId}")
    public Object getBootatrapTableResponse(Integer pageSize, Integer pageNumber, String searchText,
                                            String sortName, String sortOrder,@PathVariable String sqlId){
        Response<PageResultForBootstrap> response = sqlDefineService.getBootstrapTableResponse(sqlId);
        if(response.checkSuccess()){
            return response.getResult();
        }
        return Response.FAILURE();
    }

//    StringBuilder sb = new StringBuilder();
//        try(BufferedReader reader = httpServletRequest.getReader();) {
//        char[]buff = new char[1024];
//        int len;
//        while((len = reader.read(buff)) != -1) {
//            sb.append(buff,0, len);
//        }
//    }catch (IOException e) {
//        e.printStackTrace();
//    }
//        System.out.println(sb.toString()+"<<<<<<<<<<<<<<<<<");
}
