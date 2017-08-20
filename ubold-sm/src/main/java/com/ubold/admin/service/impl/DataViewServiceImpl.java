package com.ubold.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.ubold.admin.domain.DataView;
import com.ubold.admin.repository.DataViewRepository;
import com.ubold.admin.repository.impl.JpaRepositoryImpl;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import com.ubold.admin.util.GUID;
import com.ubold.admin.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkning on 2017/8/13.
 */
@Service
public class DataViewServiceImpl  extends JpaRepositoryImpl<DataViewRepository> implements DataViewService {

    @Override
    public Response persistent(DataViewCreateRequest request) {
        DataView dataView = this.createDataView(request);

//        数据校验
        Response response = this.checkRequest(dataView);
        if(!response.checkSuccess()){
            return response;
        }
        // 编码是否重复
        List<DataView> dataViewList;
        if(StringUtils.isBlank(dataView.getId())){
            dataViewList = this.getRepository().findByDataViewCode(dataView.getDataViewCode());
            dataView.setId(GUID.nextId());
        }else{
            dataViewList = this.getRepository().findByDataViewCodeAndIdNot(dataView.getDataViewCode(),dataView.getId());
        }
        if(CollectionUtils.isNotEmpty(dataViewList)){
            return Response.FAILURE("已存在视图编号:"+ dataView.getDataViewCode());
        }
        this.getRepository().save(dataView);
        return Response.SUCCESS();
    }

    private DataView createDataView(DataViewCreateRequest request){
        DataView dataView = new DataView();
        BeanUtils.copyProperties(request, dataView);
        dataView.setButtons(JSON.toJSONString(request.getButtons()));
        dataView.setColumns(JSON.toJSONString(request.getColumns()));
        dataView.setDataFilters(JSON.toJSONString(request.getDataFilters()));
        dataView.setOptions(JSON.toJSONString(request.getOptions()));
        dataView.setTreeOptions(JSON.toJSONString(request.getTreeOptions()));
        return dataView;
    }

    /**
     * 校验视图数据
     * @param dataView
     * @return
     */
    private Response  checkRequest(DataView dataView){
        //TODO 各数据校验
        return Response.SUCCESS();
    }

    @Override
    public Response<DataView> findByDataViewCode(String dataViewCode) {
        List<DataView> dataViewList = this.getRepository().findByDataViewCode(dataViewCode);
        if(CollectionUtils.isEmpty(dataViewList)){
            return Response.FAILURE("视图未定义,视图编号:"+dataViewCode);
        }
        DataView dataView = dataViewList.get(0);
        return Response.SUCCESS(this.parse(dataView));
    }

    private DataViewCreateRequest parse(DataView dataView){
        DataViewCreateRequest request = new DataViewCreateRequest();
        BeanUtils.copyProperties(dataView, request);
        request.setButtons(JSON.parseArray(dataView.getButtons(), ButtonVo.class));
        request.setColumns(JSON.parseArray(dataView.getColumns(), ColumnVo.class));
        request.setDataFilters(JSON.parseArray(dataView.getDataFilters(), DataFilterVo.class));
        request.setOptions(JSON.parseObject(dataView.getOptions(), OptionsVo.class));
        request.setTreeOptions(JSON.parseObject(dataView.getTreeOptions(), TreeOptionsVo.class));
        return request;
    }
}
