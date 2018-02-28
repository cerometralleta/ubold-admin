package com.ubold.admin.service.impl;

import com.ubold.admin.domain.SqlDefine;
import com.ubold.admin.repository.SqlDefineRepository;
import com.ubold.admin.request.SqlDefineRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.SqlDefineService;
import com.ubold.admin.util.GUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2017/8/30.
 */
@Service
public class SqlDefineServiceImpl implements SqlDefineService {

    @Autowired
    SqlDefineRepository sqlDefineRepository;


    @Override
    public Response persistent(SqlDefineRequest sqlDefineRequest) {
        SqlDefine sqlDefine = new SqlDefine();
        String sqlId = sqlDefineRequest.getSqlId();
        String id = sqlDefineRequest.getId();
        if (StringUtils.isBlank(id)) {
            sqlDefine.setId(GUID.nextId());
            if (null != sqlDefineRepository.findBySqlId(sqlId)) {
                return Response.FAILURE("SQLID重复");
            }
        } else if (CollectionUtils.isNotEmpty(sqlDefineRepository.findBySqlIdAndIdNot(sqlId, id))) {
            return Response.FAILURE("SQLID重复");
        }
        sqlDefine.setSqlId(sqlId);
        sqlDefine.setSqlName(sqlDefineRequest.getSqlName());
        sqlDefine.setMasterTable(sqlDefineRequest.getMasterTable());
        sqlDefine.setMasterTableId(sqlDefineRequest.getMasterTableId());
        sqlDefine.setSelectSql(sqlDefineRequest.getSelectSql());
        sqlDefineRepository.save(sqlDefine);
        return Response.SUCCESS();
    }
}
