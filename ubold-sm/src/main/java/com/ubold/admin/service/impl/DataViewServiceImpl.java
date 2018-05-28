package com.ubold.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.ubold.admin.constant.SqlDefineStatusEnum;
import com.ubold.admin.domain.DataView;
import com.ubold.admin.domain.SqlDefine;
import com.ubold.admin.repository.DataViewRepository;
import com.ubold.admin.request.DataViewCreateRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import com.ubold.admin.service.SqlIdJdbcService;
import com.ubold.admin.util.GUID;
import com.ubold.admin.utils.SimpleUtils;
import com.ubold.admin.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zkning on 20da't17/8/13.
 */
@Service
public class DataViewServiceImpl implements DataViewService {

    @Autowired
    DataViewRepository dataViewRepository;
    @Autowired
    SqlIdJdbcService sqlIdJdbcService;

    @Override
    public Response persistent(DataViewCreateRequest request) {
        // 编码是否重复
        List<DataView> dataViewList;
        DataView dataView = new DataView();
        if (StringUtils.isBlank(request.getId())) {
            dataViewList = dataViewRepository.findByDataViewCode(request.getDataViewCode());
            dataView.setId(GUID.nextId());
        } else {
            dataView = dataViewRepository.findOne(request.getId());
            dataViewList = dataViewRepository.findByDataViewCodeAndIdNot(request.getDataViewCode(), request.getId());
        }
        if (CollectionUtils.isNotEmpty(dataViewList)) {
            return Response.FAILURE("已存在视图编号:" + request.getDataViewCode());
        }
        dataView.setSqlId(request.getSqlId());
        dataView.setRemark(request.getRemark());
        dataView.setDataViewCode(request.getDataViewCode());
        dataView.setDataViewName(request.getDataViewName());
        dataView.setButtons(JSON.toJSONString(request.getButtons()));
        dataView.setColumns(JSON.toJSONString(request.getColumns()));
        dataView.setDataFilters(JSON.toJSONString(request.getDataFilters()));
        dataView.setOptions(JSON.toJSONString(request.getOptions()));
        dataView.setTreeOptions(JSON.toJSONString(request.getTreeOptions()));
        dataView.setVersion(request.getVersion());
        dataViewRepository.save(dataView);
        return Response.SUCCESS();
    }

    /**
     * 创建dataView
     *
     * @param request
     * @return
     */
    private DataView createDataView(DataViewCreateRequest request) {
        DataView dataView = new DataView();
        dataView.setId(request.getId());
        dataView.setSqlId(request.getSqlId());
        dataView.setRemark(request.getRemark());
        dataView.setDataViewCode(request.getDataViewCode());
        dataView.setDataViewName(request.getDataViewName());
        dataView.setButtons(JSON.toJSONString(request.getButtons()));
        dataView.setColumns(JSON.toJSONString(request.getColumns()));
        dataView.setDataFilters(JSON.toJSONString(request.getDataFilters()));
        dataView.setOptions(JSON.toJSONString(request.getOptions()));
        dataView.setTreeOptions(JSON.toJSONString(request.getTreeOptions()));
        dataView.setVersion(request.getVersion());
        return dataView;
    }

    @Override
    public Response<DataView> findByDataViewCode(String dataViewCode) {
        List<DataView> dataViewList = dataViewRepository.findByDataViewCode(dataViewCode);
        DataView dataView = dataViewList.get(0);
        return Response.SUCCESS(this.parseResult(dataView));
    }

    @Override
    public List<DataView> queryByDataViewCode(String code) {
        return dataViewRepository.findByDataViewCode(code);
    }

    @Override
    public DataView getByDataViewCode(String dataViewCode) {
        List<DataView> dataViewList = dataViewRepository.findByDataViewCode(dataViewCode);
        if (CollectionUtils.isNotEmpty(dataViewList)) {
            return dataViewList.get(0);
        }
        return null;
    }

    @Override
    public Response<SqlDefine> queryTableschemaInfo(QuerytableParam querytableParam) {
        SqlDefine sqlDefine = new SqlDefine();
        sqlDefine.setMasterTable(querytableParam.getTablename());
        List<SQLColumnschemaResult> columnList = sqlIdJdbcService.queryColumnschema(querytableParam);
        for(SQLColumnschemaResult sqlColumnschemaResult : columnList){
            if(StringUtils.isNoneBlank(sqlColumnschemaResult.getColumnKey())){
                sqlDefine.setMasterTableId(sqlColumnschemaResult.getColumnName());
                break;
            }
        }
        sqlDefine.setStatus(SqlDefineStatusEnum.UN_ISSUE.getCode());
        sqlDefine.setSelectSql(SimpleUtils.createQuerySql(querytableParam.getTablename(),columnList));
        return Response.SUCCESS(sqlDefine);
    }

    /**
     * 转换为页面显示数据
     *
     * @param dataView
     * @return
     */
    private DataViewCreateRequest parseResult(DataView dataView) {
        DataViewCreateRequest request = new DataViewCreateRequest();
        request.setButtons(JSON.parseArray(dataView.getButtons(), ButtonParam.class));
        request.setColumns(JSON.parseArray(dataView.getColumns(), ColumnParam.class));
        request.setDataFilters(JSON.parseArray(dataView.getDataFilters(), DataFilterParam.class));
        request.setOptions(JSON.parseObject(dataView.getOptions(), OptionsParam.class));
        request.setTreeOptions(JSON.parseObject(dataView.getTreeOptions(), TreeOptionsParam.class));
        request.setId(dataView.getId());
        request.setSqlId(dataView.getSqlId());
        request.setRemark(dataView.getRemark());
        request.setDataViewCode(dataView.getDataViewCode());
        request.setDataViewName(dataView.getDataViewName());
        request.setVersion(dataView.getVersion());
        return request;
    }

}
