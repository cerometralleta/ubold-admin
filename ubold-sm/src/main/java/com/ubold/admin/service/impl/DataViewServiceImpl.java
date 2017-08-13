package com.ubold.admin.service.impl;

import com.ubold.admin.domain.DataView;
import com.ubold.admin.repository.DataViewRepository;
import com.ubold.admin.repository.JpaRepository;
import com.ubold.admin.repository.impl.JpaRepositoryImpl;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import com.ubold.admin.util.GUID;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by zkning on 2017/8/13.
 */
@Service
public class DataViewServiceImpl  extends JpaRepositoryImpl<DataViewRepository> implements DataViewService {

    @Override
    public Response create(DataViewCreateRequest request) {
        DataView dataView = new DataView();
        BeanUtils.copyProperties(request, dataView);

//        数据校验

//        1、编码是否重复

        dataView.setId(GUID.nextId());
        this.getRepository().save(dataView);
        return Response.SUCCESS();
    }
}
