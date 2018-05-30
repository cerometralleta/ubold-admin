package com.ubold.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.request.ZtreeParamsRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.model.*;

import java.util.List;

public interface SqlIdJdbcService {


    //根据sql获取该查询语句的列信息
    public List<ColumnParam> getColumnsBySqlId(String sqlId);

    //根据sqlid构建bootstrap table数据
    Response<BootstrapPageResult> getBootstrapTableResponse(Integer pageSize, Integer pageNumber, String searchText,
            String sortName, String sortOrder, String sqlId, BootstrapSearchParam bootstrapSearchParam);

    //根据sqlid获取bootstrap table数据
    Response<BootstrapPageResult> getBootstrapTableResponse(BootstrapSearchParam bootstrapSearchParam, String sqlId);

    //查询内容根据sqlid和数据id
    Response fetch(SqlDefineFetchParam sqlDefineFetchParam);

    //创建数据视图根据SQL定义编码
    Response createByDataViewCode(String code, JSONObject rowValue);

    //删除数据视图根据SQL定义编码
    Response deleteByDataViewCode(String code, JSONObject rowValue);

    //更新数据视图根据SQL定义编码
    Response modifyByDataViewCode(String code, JSONObject rowValue);

    //构建ztree数据
    Response ztree(ZtreeParamsRequest ztreeParamsRequest);

    //查找系统表
    List<FindTablesResult> findTables(FindTablesParam findTablesParam);


    //根据表获取columns
    List<GetColumnsResult> getColumns(String tablename, String datasource);
}
