package com.ubold.admin.service;

import com.ubold.admin.repository.FormViewRepository;
import com.ubold.admin.repository.JpaRepository;
import com.ubold.admin.request.FormViewRequest;
import com.ubold.admin.response.Response;

/**
 * Created by lenovo on 2017/8/28.
 */
public interface FormViewService{

    /**
     * 保存
     * @param request
     * @return
     */
    Response persistent(FormViewRequest request);

    /**
     * 根据编号获取FormView配置
     * @param code
     * @return
     */
    Response findByCode(String code);
}
