package com.ubold.admin.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.request.SqlDefineRequest;
import com.ubold.admin.request.ZtreeParamsRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.SqlDefineService;
import com.ubold.admin.service.SqlIdJdbcService;
import com.ubold.admin.vo.BootstrapPageResult;
import com.ubold.admin.vo.BootstrapSearchParam;
import com.ubold.admin.vo.ColumnParam;
import com.ubold.admin.vo.SqlDefineFetchParam;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

/**
 * Created by lenovo on 2017/8/30.
 */
@RestController
@RequestMapping("/sm/sql")
public class SqlDefineController {

    @Autowired
    SqlDefineService sqlDefineService;

    @Autowired
    SqlIdJdbcService sqlIdJdbcService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @ResponseBody
    @RequestMapping(value="/persistent",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response persistent(@RequestBody @Valid SqlDefineRequest sqlDefineRequest) {
        return sqlDefineService.persistent(sqlDefineRequest);
    }

    @ResponseBody
    @RequestMapping(value="/createColumnList/{sqlId}",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response createColumnList(@PathVariable String sqlId) {
        List<ColumnParam> list = sqlIdJdbcService.getColumnsBySqlId(sqlId);
        return Response.SUCCESS(list);
    }

    /**
     * 保存SQLVIEW记录
     * @param formParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/create/{code}",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object createView(@PathVariable String code,@RequestBody JSONObject formParam) {
        return sqlIdJdbcService.createByDataViewCode(code, formParam);
    }


    /**
     * 修改视图的数据
     * @param code
     * @param formParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/modfity/{code}",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object modfityView(@PathVariable String code,@RequestBody JSONObject formParam) {
        return sqlIdJdbcService.modifyByDataViewCode(code, formParam);
    }

    /**
     * 删除视图的数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete/{code}",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  Object deleteView(@PathVariable String code,@RequestBody JSONObject row) {
        return sqlIdJdbcService.deleteByDataViewCode(code, row);
    }

    @ResponseBody
    @RequestMapping(value="/fetch",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response fetch(@RequestBody @Valid SqlDefineFetchParam sqlDefineFetchParam) {
        Response response = sqlIdJdbcService.fetch(sqlDefineFetchParam);
        return response;
    }

    /**
     * Ztree
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/ztree",method= RequestMethod.POST)
    public Object ztree(ZtreeParamsRequest ztreeParamsRequest) {
        Response response = sqlIdJdbcService.ztree(ztreeParamsRequest);
        return response.getResult();
    }

    /**
     * dataViewCode  根据SQLID返回bootstrapTable数据格式
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/bootstrap/{sqlId}",method= RequestMethod.POST)
    public Object getBootatrapTableResponse(Integer pageSize, Integer pageNumber, String searchText,
                                            String sortName, String sortOrder,@PathVariable String sqlId,
                                            @RequestBody BootstrapSearchParam bootstrapSearchParam) throws UnsupportedEncodingException {
        Response<BootstrapPageResult> response;
        if(RequestMethod.GET.name().equals( httpServletRequest.getMethod())){
            //当查询条件中包含中文时，get请求默认会使用ISO-8859-1编码请求参数，在服务端需要对其解码
            if (!StringUtils.isEmpty(searchText)) {
                searchText = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
            }
            response = sqlIdJdbcService.getBootstrapTableResponse(pageSize, pageNumber, searchText, sortName, sortOrder,
                    sqlId, bootstrapSearchParam);
        }else{
            response = sqlIdJdbcService.getBootstrapTableResponse(bootstrapSearchParam, sqlId);
        }
        if(response.checkSuccess()){
            return response.getResult();
        }
        return Response.FAILURE();
    }

    @ResponseBody
    @RequestMapping(value="/getCode/{prefix}",method= RequestMethod.POST)
    public Response getCode(@PathVariable String prefix) {
        String sdf = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return Response.SUCCESS(prefix + sdf);
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
