package com.ubold.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.ubold.admin.domain.FormView;
import com.ubold.admin.repository.FormViewRepository;
import com.ubold.admin.repository.impl.JpaRepositoryImpl;
import com.ubold.admin.request.FormViewRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.FormViewService;
import com.ubold.admin.util.GUID;
import com.ubold.admin.vo.FieldParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/8/28.
 */
@Service
public class FormViewServiceImpl  extends JpaRepositoryImpl<FormViewRepository> implements FormViewService{

    @Override
    public Response persistent(FormViewRequest request) {
        FormView formView = new FormView();
        List<FormView> formViewList;
        if(StringUtils.isBlank(request.getId())){
            formViewList = this.getRepository().findByCode(request.getCode());
            formView.setId(GUID.nextId());
        }else{
            formViewList = this.getRepository().findByCodeAndIdNot(request.getCode(),request.getId());
        }
        if(CollectionUtils.isNotEmpty(formViewList)){
            return Response.FAILURE("编号重复:"+request.getCode());
        }
        formView.setCode(request.getCode());
        formView.setSqlId(request.getSqlId());
        formView.setColumns(JSON.toJSONString(request.getColumns()));
        formView.setRemark(request.getRemark());
        formView.setUrl(request.getUrl());
        formView.setVersion(request.getVersion());
        this.getRepository().save(formView);
        return null;
    }

    @Override
    public Response findByCode(String code) {
        List<FormView> dataViewList = this.getRepository().findByCode(code);
        if(CollectionUtils.isEmpty(dataViewList)){
            return Response.FAILURE("表单未定义,编号:" + code);
        }
        FormView formView = dataViewList.get(0);
        FormViewRequest request = new FormViewRequest();
        request.setCode(formView.getCode());
        request.setSqlId(formView.getSqlId());
        request.setColumns(JSON.parseArray(formView.getColumns(),FieldParam.class));
        request.setRemark(formView.getRemark());
        request.setUrl(formView.getUrl());
        request.setVersion(formView.getVersion());
        return Response.SUCCESS(request);
    }
}
