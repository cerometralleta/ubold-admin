package com.ubold.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.request.ZtreeParamsRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.model.*;

import java.util.List;

public interface SqlIdJdbcService {


    /**
     * 根据SQLID获取所有列信息
     */
    public List<ColumnParam> getColumnsBySqlId(String sqlId);

    Response<BootstrapPageResult> getBootstrapTableResponse(Integer pageSize, Integer pageNumber, String searchText,
            String sortName, String sortOrder, String sqlId, BootstrapSearchParam bootstrapSearchParam);

    Response<BootstrapPageResult> getBootstrapTableResponse(BootstrapSearchParam bootstrapSearchParam, String sqlId);

    Response fetch(SqlDefineFetchParam sqlDefineFetchParam);

    Response createByDataViewCode(String code, JSONObject rowValue);

    Response deleteByDataViewCode(String code, JSONObject rowValue);

    Response modifyByDataViewCode(String code, JSONObject rowValue);

    Response ztree(ZtreeParamsRequest ztreeParamsRequest);

    List<SQLTableschemaResult> queryTableschema(QuerytableParam querytableParam);
    List<SQLColumnschemaResult> queryColumnschema(QuerytableParam querytableParam);
}
