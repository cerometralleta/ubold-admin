package com.ubold.admin.service;

import com.ubold.admin.repository.DataViewRepository;
import com.ubold.admin.repository.JpaRepository;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;

/**
 * 数据视图服务
 * Created by zkning on 2017/8/13.
 */
public interface DataViewService extends JpaRepository<DataViewRepository> {

    Response create(DataViewCreateRequest request);
}
