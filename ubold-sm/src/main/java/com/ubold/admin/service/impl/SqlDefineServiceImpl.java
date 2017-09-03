package com.ubold.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.constant.ComponentType;
import com.ubold.admin.constant.SqlDefineConstant;
import com.ubold.admin.domain.DataView;
import com.ubold.admin.domain.SqlDefine;
import com.ubold.admin.repository.SqlDefineRepository;
import com.ubold.admin.repository.impl.JpaRepositoryImpl;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import com.ubold.admin.service.FormViewService;
import com.ubold.admin.service.SqlDefineService;
import com.ubold.admin.util.GUID;
import com.ubold.admin.utils.SimpleUtils;
import com.ubold.admin.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/30.
 */
@Service
public class SqlDefineServiceImpl extends JpaRepositoryImpl<SqlDefineRepository> implements SqlDefineService {

    @Autowired
    FormViewService formViewService;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    DataViewService dataViewService;

    @Override
    public Response persistent(JSONObject paramJson) {
        SqlDefine sqlDefine = new SqlDefine();
        String sqlId  = paramJson.getString("sqlId");
        String id = paramJson.getString("id");
        if(StringUtils.isBlank(id)){
            sqlDefine.setId(GUID.nextId());
            if(null != this.getRepository().findBySqlId(sqlId)){
                return Response.FAILURE("SQLID重复");
            }
        }else{
            if(CollectionUtils.isNotEmpty(this.getRepository().findBySqlIdAndIdNot(sqlId,id))){
                return Response.FAILURE("SQLID重复");
            }
        }
        sqlDefine.setSqlId(sqlId);
        sqlDefine.setSqlName(paramJson.getString("sqlName"));
        sqlDefine.setMasterTable(paramJson.getString("masterTable"));
        sqlDefine.setMasterTableId(paramJson.getString("masterTableId"));
        sqlDefine.setSelectSql(paramJson.getString("selectSql"));
        this.getRepository().save(sqlDefine);
        return Response.SUCCESS();
    }

    @Override
    public List<ColumnParam> getColumnsBySqlId(String sqlId) {
        List<ColumnParam> list = new ArrayList<ColumnParam>();
        SqlDefine sqlDefine = this.getRepository().findBySqlId(sqlId);

        //获取主表实际列用来过滤
        String masterSql = "SELECT * FROM " + sqlDefine.getMasterTable() + " T WHERE 1=2 " ;
        SqlRowSet resultSet = namedParameterJdbcTemplate.queryForRowSet(masterSql,new HashMap<String,String>());
        SqlRowSetMetaData srsmd = resultSet.getMetaData();
        Map<String,Object> masterFieldMap = new HashMap<>();
        for (int i = 1; i < srsmd.getColumnCount() + 1; i++) {
            masterFieldMap.put(srsmd.getColumnLabel(i), srsmd.getColumnLabel(i));
        }

        //获取查询所有列
        String viewSql = " SELECT T.* FROM (" + sqlDefine.getSelectSql()+") T WHERE 1=2 ";

        //通过临时表 找到对应的字段属性
        resultSet = namedParameterJdbcTemplate.queryForRowSet(viewSql,new HashMap<String,String>());
        srsmd = resultSet.getMetaData();
        ColumnParam field = null;
        for (int i = 1; i < srsmd.getColumnCount() + 1; i++) {
            field = new ColumnParam();
            field.setField(srsmd.getColumnLabel(i));// as 后的值 ，getColumnName 原始值
            field.setMaxlength(srsmd.getPrecision(i));
            field.setDataType(srsmd.getColumnTypeName(i));
            field.setFieldType(ComponentType.TEXT.getValue());
            //判断是否是日期类型
            if (SimpleUtils.getDataType(field.getDataType()).equals(SqlDefineConstant.COLUMNTYPE_DATE)) {
                field.setFieldType(ComponentType.DATEPICKER.getValue());
            }
            field.setIdx(i-1);
            if(masterFieldMap.containsKey(field.getField())){

                //修改类型
                field.setUpdateType(SqlDefineConstant.MODIFTY_ENABLE);
                field.setInset(true);
                field.setVisible(true);
                field.setCardVisible(true);
                field.setSwitchable(true);
                field.setView(true);

                //是否增加
                if((StringUtils.isNoneBlank(sqlDefine.getMasterTableId()) &&
                        field.getField().equalsIgnoreCase(sqlDefine.getMasterTableId()))||
                        SqlDefineConstant.VERSION.equals(field.getField().toUpperCase())
                        ){
                    field.setInset(false);
                    field.setUpdateType(SqlDefineConstant.MODIFTY_HIDE);
                }
            }
            this.setFieldTitle(field, srsmd.getColumnLabel(i));
            list.add(field);
        }
        return list;
    }

    @Override
    public Response deleteByDataViewCode(String code,JSONObject row){
        List<DataView> dataViewList = dataViewService.getRepository().findByDataViewCode(code);
        if(CollectionUtils.isEmpty(dataViewList)){
            return Response.FAILURE();
        }
        DataView dataView = dataViewList.get(0);
        //获取sqlDefine
        SqlDefine sqlDefine = getRepository().findBySqlId(dataView.getSqlId());
        if(StringUtils.isEmpty(sqlDefine.getMasterTableId())){
            return Response.FAILURE("未配置主键");
        }

        //delete SQL
        StringBuffer deleteSql = new StringBuffer(" DELETE FROM ")
                .append(sqlDefine.getMasterTable())
                .append(" WHERE ")
                .append(sqlDefine.getMasterTableId())
                .append(" = :").append(sqlDefine.getMasterTableId());

        //参数
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put(sqlDefine.getMasterTableId(),row.get(sqlDefine.getMasterTableId()));
        if(namedParameterJdbcTemplate.update(deleteSql.toString(), paramMap) < 1){
            return Response.FAILURE(deleteSql);
        }
        return Response.SUCCESS();
    }

    @Override
    public Response modifyByDataViewCode(String code,JSONObject row){
        List<DataView> dataViewList = dataViewService.getRepository().findByDataViewCode(code);
        if(CollectionUtils.isEmpty(dataViewList)){
            return Response.FAILURE();
        }
        DataView dataView = dataViewList.get(0);

        //获取sqlDefine
        SqlDefine sqlDefine = getRepository().findBySqlId(dataView.getSqlId());
        //获取修改列
        List<ColumnParam> dataViewFields = JSON.parseArray(dataView.getColumns(),ColumnParam.class);
        if(CollectionUtils.isEmpty(dataViewFields)){
            return Response.FAILURE("视图编号:"+ dataView.getSqlId() + "未设置字段列表");
        }

        //update SQL
        StringBuffer modifySQL = new StringBuffer(" UPDATE ")
                .append(sqlDefine.getMasterTable())
                .append(" SET ");

        //参数
        Map<String,Object> paramMap = new HashMap<>();
        for(ColumnParam field : dataViewFields){
            if(SqlDefineConstant.MODIFTY_ENABLE.equals(field.getUpdateType())){
                modifySQL.append(field.getField()).append("= :")
                        .append(field.getField())
                        .append(",");
                paramMap.put(field.getField(), row.get(field.getField()));
            }
        }
        modifySQL.deleteCharAt(modifySQL.lastIndexOf(","));

        //是否包含主键
        if(StringUtils.isBlank(sqlDefine.getMasterTableId())){
            return Response.FAILURE("SQLID:"+dataView.getSqlId()+",未设置主键");
        }
        modifySQL.append(" WHERE ")
                .append(sqlDefine.getMasterTableId())
                .append("= :").append(sqlDefine.getMasterTableId());
        paramMap.put(sqlDefine.getMasterTableId(),row.get(sqlDefine.getMasterTableId()));
        if(namedParameterJdbcTemplate.update(modifySQL.toString(), paramMap) < 1){
          Response.FAILURE(modifySQL);
        }
        return Response.SUCCESS();
    }

    @Override
    public Response createByDataViewCode(String code,JSONObject row){
        List<DataView> dataViewList = dataViewService.getRepository().findByDataViewCode(code);
        if(CollectionUtils.isEmpty(dataViewList)){
            return Response.FAILURE();
        }
        DataView dataView = dataViewList.get(0);

        //获取sqlDefine
        SqlDefine sqlDefine = getRepository().findBySqlId(dataView.getSqlId());

        //获取修改列
        List<ColumnParam> dataViewFields = JSON.parseArray(dataView.getColumns(),ColumnParam.class);
        if(CollectionUtils.isEmpty(dataViewFields)){
            return Response.FAILURE("视图编号:"+ dataView.getSqlId() + "未设置字段列表");
        }

        //insert SQL
        StringBuffer insertSQL = new StringBuffer("INSERT INTO ")
                .append(sqlDefine.getMasterTable())
                .append("(");

        //输入
        StringBuffer values = new StringBuffer("(");

        //参数
        Map<String,Object> paramMap = new HashMap<>();
        for(ColumnParam field : dataViewFields){
            if(field.isInset()){
                insertSQL.append(field.getField()).append(",");
                values.append(":").append(field.getField()).append(",");
                paramMap.put(field.getField(), row.get(field.getField()));
            }
        }
        //自动生成id
        if(StringUtils.isNotBlank(sqlDefine.getMasterTableId())){
            insertSQL.append(sqlDefine.getMasterTableId()).append(",");
            values.append(":").append(sqlDefine.getMasterTableId()).append(",");
            paramMap.put(sqlDefine.getMasterTableId(), GUID.nextId());
        }else{
            //是否包含主键
            return Response.FAILURE("SQLID:"+dataView.getSqlId()+",未设置主键");
        }
        insertSQL.deleteCharAt(insertSQL.lastIndexOf(",")).append(") VALUES ");
        values = values.deleteCharAt(values.lastIndexOf(",")).append(")");
        String sql = insertSQL.append(values).toString();
        if(namedParameterJdbcTemplate.update(sql, paramMap) < 1) {
            return Response.FAILURE(sql);
        }
        return Response.SUCCESS();
    }

    @Override
    public Response fetch(SqlDefineFetchParam sqlDefineFetchParam) {
        //根据SQLid获取查询语句
        SqlDefine sqlDefine = this.getRepository().findBySqlId(sqlDefineFetchParam.getSqlId());
        StringBuilder sqlBuilder = new StringBuilder("select t.* from (")
                .append(sqlDefine.getSelectSql()).append(") t ")
                .append(" where ")
        .append(sqlDefine.getMasterTableId()).append("=").append(":id");
        Map<String,Object> paraMap = new HashedMap();
        paraMap.put("id",sqlDefineFetchParam.getId());
        return  Response.SUCCESS(namedParameterJdbcTemplate.queryForMap(sqlBuilder.toString(),paraMap));
    }

    @Override
    public Response<BootstrapPageResult> getBootstrapTableResponse(BootstrapSearchParam bootstrapSearchParam, String sqlId) {
        return this.getBootstrapTableResponse(bootstrapSearchParam.getPageSize(),bootstrapSearchParam.getPageNumber()
        ,bootstrapSearchParam.getSearchText(),bootstrapSearchParam.getSortName(),bootstrapSearchParam.getSortOrder(),sqlId
                , bootstrapSearchParam.getSearchArray());
    }

    /**
     * 设置名称
     * @param sqlViewField
     * @param field
     */
    private void setFieldTitle(ColumnParam sqlViewField, String field){
        switch (field.toUpperCase()) {
            case SqlDefineConstant.LAST_UPDATE_TIME:
                sqlViewField.setTitle("更新时间");
                sqlViewField.setVisible(false);
                break;
            case SqlDefineConstant.LAST_UPDATE_USER:
                sqlViewField.setTitle("更新者");
                sqlViewField.setVisible(false);
                break;
            case SqlDefineConstant.CREATE_TIME:
                sqlViewField.setTitle("创建时间");
                sqlViewField.setVisible(false);
                break;
            case SqlDefineConstant.CREATE_USER:
                sqlViewField.setTitle("创建者");
                sqlViewField.setVisible(false);
                break;
            case SqlDefineConstant.VERSION:
                sqlViewField.setTitle("版本号");
                sqlViewField.setVisible(false);
                break;
            default:
                sqlViewField.setTitle(field);
                break;
        }
    }

    /**
     * 默认获取bootstrapTable服务
     * @param sqlId
     * @return
     */
    @Override
    public Response<BootstrapPageResult> getBootstrapTableResponse(Integer pageSize, Integer pageNumber, String searchText,
                                                                   String sortName, String sortOrder, String sqlId,
                                                                   List<ConditionParam> conditionParamList) {
        BootstrapPageResult pageResultForBootstrap = new BootstrapPageResult();
        SqlDefine sqlDefine = this.getRepository().findBySqlId(sqlId);
        StringBuilder sqlBuilder = new StringBuilder(sqlDefine.getSelectSql());
        if(StringUtils.isNoneBlank(sqlDefine.getSqlExpand())){
            sqlBuilder.append(sqlDefine.getSqlExpand());
        }
        if(StringUtils.isNoneBlank(sqlDefine.getSqldesc())){
            sqlBuilder.append(sqlDefine.getSqldesc());
        }
        StringBuilder pageBuilder = new StringBuilder("select t.* from (");
        pageBuilder.append(sqlBuilder.toString()).append(") t ");
        Map<String,Object> paraMap = new HashedMap();

        //条件
        if(CollectionUtils.isNotEmpty(conditionParamList)){
            pageBuilder.append(" where 1=1 ");
            for(ConditionParam conditionParam : conditionParamList){
                if(StringUtils.isNoneBlank(conditionParam.getValue())){
                    pageBuilder.append(" and t.").append(conditionParam.getField()).append(" ") .append(conditionParam.getExpression());
                    if("like".equalsIgnoreCase(conditionParam.getExpression())){
                        pageBuilder .append(" %:").append(conditionParam.getField());
                    }else{
                        pageBuilder .append(" :").append(conditionParam.getField());
                    }
                    paraMap.put(conditionParam.getField(),conditionParam.getValue());
                }
            }
        }

        //排序
        if(StringUtils.isNoneBlank(sortName)){
             pageBuilder.append(" order by ").append(sortName) .append(" ") .append(sortOrder);
         }
        pageBuilder.append(" limit ").append((pageNumber - 1) * pageSize ).append(",").append(pageSize);
        logger.info(pageBuilder.toString());
        List<Map<String,Object>> list = namedParameterJdbcTemplate.queryForList(pageBuilder.toString(), paraMap);
        pageResultForBootstrap.setRows(list);

        //查询总数
        StringBuilder countBuilder = new StringBuilder("select count(1) from (").append(sqlBuilder).append(") total");
        Long count =  namedParameterJdbcTemplate.queryForObject(countBuilder.toString(),new HashMap(),Long.class);
        logger.info(countBuilder.toString());
        pageResultForBootstrap.setTotal(count);
        return Response.SUCCESS(pageResultForBootstrap);
    }


}
