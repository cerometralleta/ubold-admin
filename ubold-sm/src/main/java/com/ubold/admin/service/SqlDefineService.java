package com.ubold.admin.service;

import com.ubold.admin.request.SqlDefineRequest;
import com.ubold.admin.response.Response;

/**
 * Created by lenovo on 2017/8/30.
 */
public interface SqlDefineService {

    /**
     * 保存SQL
     * @param paramJson
     * @return
     */
    Response persistent(SqlDefineRequest sqlDefineRequest);
}
