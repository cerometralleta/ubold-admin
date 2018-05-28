package com.ubold.admin.service;

import com.ubold.admin.domain.DataView;
import com.ubold.admin.domain.SqlDefine;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.model.QuerytableParam;

import java.util.List;

/**
 * 数据视图服务
 * Created by zkning on 2017/8/13.
 */
public interface DataViewService {

    Response persistent(DataViewCreateRequest request);

    /**
     * //TODO 缓存视图数据
     * 根据编号获取视图信息
     * @param dataViewCode
     * @return
     */
    Response<DataView> findByDataViewCode(String dataViewCode);

    List<DataView> queryByDataViewCode(String dataViewCode);

    DataView getByDataViewCode(String dataViewCode);
    Response<SqlDefine> queryTableschemaInfo(QuerytableParam querytableParam);
}
