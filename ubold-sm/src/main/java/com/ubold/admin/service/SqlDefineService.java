package com.ubold.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.repository.JpaRepository;
import com.ubold.admin.repository.SqlDefineRepository;
import com.ubold.admin.request.ZtreeParamsRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.vo.*;

import java.util.List;

/**
 * Created by lenovo on 2017/8/30.
 */
public interface SqlDefineService  extends JpaRepository<SqlDefineRepository> {

    /**
     * 保存SQL
     * @param paramJson
     * @return
     */
    Response persistent(JSONObject paramJson);

    /**
     * 根据SQLID获取所有列信息
     * @param sqlId
     * @return
     */
    public List<ColumnParam> getColumnsBySqlId(String sqlId);

    Response<BootstrapPageResult> getBootstrapTableResponse(Integer pageSize, Integer pageNumber, String searchText,
                                                            String sortName, String sortOrder, String sqlId,BootstrapSearchParam bootstrapSearchParam);

    Response<BootstrapPageResult> getBootstrapTableResponse(BootstrapSearchParam bootstrapSearchParam, String sqlId);

    Response fetch(SqlDefineFetchParam sqlDefineFetchParam);

    Response createByDataViewCode(String code,JSONObject row);
    Response deleteByDataViewCode(String code,JSONObject row);
    Response modifyByDataViewCode(String code,JSONObject row);
    Response ztree(ZtreeParamsRequest ztreeParamsRequest);
}
