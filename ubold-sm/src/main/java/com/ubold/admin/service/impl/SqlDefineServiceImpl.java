package com.ubold.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.constant.ComponentType;
import com.ubold.admin.constant.SqlDefineConstant;
import com.ubold.admin.constant.SqlExpression;
import com.ubold.admin.constant.TreeNodeHandleType;
import com.ubold.admin.domain.DataView;
import com.ubold.admin.domain.SqlDefine;
import com.ubold.admin.repository.SqlDefineRepository;
import com.ubold.admin.repository.impl.JpaRepositoryImpl;
import com.ubold.admin.request.ZtreeParamsRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import com.ubold.admin.service.FormViewService;
import com.ubold.admin.service.SqlDefineService;
import com.ubold.admin.util.GUID;
import com.ubold.admin.utils.DataFilter;
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
            field.setAlign(SqlDefineConstant.align_center);
            field.setHalign(SqlDefineConstant.align_center);
            field.setValign(SqlDefineConstant.valign_middle);
            field.setFalign(SqlDefineConstant.valign_middle);
            field.setUniqueCheck(false);
            //判断是否是日期类型
            if (SimpleUtils.getDataType(field.getDataType()).equals(SqlDefineConstant.COLUMNTYPE_DATE)) {
                field.setFieldType(ComponentType.DATEPICKER.getValue());
            }
            if (SimpleUtils.getDataType(field.getDataType()).equals(SqlDefineConstant.COLUMNTYPE_TEXT)) {
                field.setFieldType(ComponentType.TEXTAREA.getValue());
            }

            if (SimpleUtils.getDataType(field.getDataType()).equals(SqlDefineConstant.COLUMNTYPE_NUMBER)) {
                field.setAlign(SqlDefineConstant.align_right);
            }

            field.setIdx(i-1);
            if(masterFieldMap.containsKey(field.getField())){

                //修改类型
                field.setUpdateType(SqlDefineConstant.MODIFTY_HIDE);
                field.setInsert(true);
                field.setVisible(true);
                field.setCardVisible(true);
                field.setSwitchable(true);
                field.setView(true);

                //是否增加
                if((StringUtils.isNoneBlank(sqlDefine.getMasterTableId()) &&
                        field.getField().equalsIgnoreCase(sqlDefine.getMasterTableId()))||
                        SqlDefineConstant.VERSION.equals(field.getField().toUpperCase())
                        ){
                    field.setInsert(false);
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
        //获取参数配置
        OptionsParam optionsParam = JSON.parseObject(dataView.getOptions(), OptionsParam.class);

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
                if(this.uniqueCheck(field,sqlDefine,row,false)){
                    return Response.FAILURE(field.getTitle() + "数据重复");
                }
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

        //根据版本号更新
        if(StringUtils.isNotBlank(optionsParam.getVersion())){
            modifySQL .append(" and ").append(optionsParam.getVersion()).append("  = :").append(optionsParam.getVersion());
            paramMap.put(optionsParam.getVersion(),row.get(optionsParam.getVersion()));
        }
        paramMap.put(sqlDefine.getMasterTableId(),row.get(sqlDefine.getMasterTableId()));
        if(namedParameterJdbcTemplate.update(modifySQL.toString(), paramMap) < 1){
          return Response.FAILURE(modifySQL);
        }
        return Response.SUCCESS();
    }

    /**
     * 数据唯一校验
     * @param field
     * @param sqlDefine
     * @param row
     * @param insert
     * @return
     */
    private boolean uniqueCheck(ColumnParam field,SqlDefine sqlDefine,JSONObject row,boolean insert){
        if(!field.isUniqueCheck()){
            return false;
        }
        Map<String,Object> checkParams = new HashedMap();
        StringBuffer checkSql = new StringBuffer("select count(1) from ") .append(sqlDefine.getMasterTable());
        if(insert){
            checkSql.append(" t where ")
                    .append(" t.").append(field.getField()).append(" = :").append(field.getField());
            checkParams.put(field.getField(),row.get(field.getField()));
        }else{
            checkSql.append(" t where t.") .append( sqlDefine.getMasterTableId())
            .append(" <> :").append(sqlDefine.getMasterTableId())
            .append(" and t.").append(field.getField()).append(" = :").append(field.getField());
            checkParams.put(sqlDefine.getMasterTableId(),row.get(sqlDefine.getMasterTableId()));
            checkParams.put(field.getField(),row.get(field.getField()));
        }
        return namedParameterJdbcTemplate.queryForObject(checkSql.toString(),checkParams,Long.class) > 0;
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
            if(field.isInsert()){
                if(this.uniqueCheck(field,sqlDefine,row,true)){
                    return Response.FAILURE(field.getTitle() + "数据重复");
                }

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
                , bootstrapSearchParam);
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
                                                                   BootstrapSearchParam bootstrapSearchParam) {
        BootstrapPageResult pageResultForBootstrap = new BootstrapPageResult();
        SqlDefine sqlDefine = this.getRepository().findBySqlId(sqlId);
        DataFilter dataFilter = DataFilter.getInstance();
        dataFilter.setQuerySql(sqlDefine.getSelectSql());
        dataFilter.setSortName(sortName);
        dataFilter.setSortOrder(sortOrder);

        //条件
        List<ConditionParam> conditionParamList = bootstrapSearchParam.getSearchArray();
        if(CollectionUtils.isEmpty(conditionParamList)){
            conditionParamList = new ArrayList<>();
        }
        //解析ztree
        ConditionParam ztreeConditionParam = this.getTreeNode(bootstrapSearchParam.getTreeOptions());
        if(null != ztreeConditionParam){
            conditionParamList.add(ztreeConditionParam);
        }
        dataFilter.addCondition(conditionParamList);
        List<Map<String,Object>> list = namedParameterJdbcTemplate.queryForList( dataFilter.createPager(pageNumber,pageSize), dataFilter.getParams());
        pageResultForBootstrap.setRows(list);

        //查询总数
        Long count =  namedParameterJdbcTemplate.queryForObject(dataFilter.countSql(),dataFilter.getParams(),Long.class);
        pageResultForBootstrap.setTotal(count);
        return Response.SUCCESS(pageResultForBootstrap);
    }


    /**
     * 获取树节点条件
     * @param treeOptionsParam
     * @return
     */
    private ConditionParam getTreeNode(TreeOptionsParam treeOptionsParam){
        if(null == treeOptionsParam || !treeOptionsParam.isShow()){
            return null;
        }
        //获取sqlDefine
        SqlDefine sqlDefine = this.getRepository().findBySqlId(treeOptionsParam.getSqlId());
        Map<String,Object> paramMap = new HashMap<String, Object>();

        //默认是空字符串
        String idValue = StringUtils.isBlank(treeOptionsParam.getNodeValue())? "#000000" : treeOptionsParam.getNodeValue();
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        switch (treeOptionsParam.getScope()) {
            case TreeNodeHandleType.TREEHANDLETYPE_ALL:
                result = findAllNode(warpTreeSql(sqlDefine.getSelectSql(), treeOptionsParam.getpIdKey()), idValue, treeOptionsParam);
                break;
            case TreeNodeHandleType.TREEHANDLETYPE_CHILD:
                paramMap.put(treeOptionsParam.getpIdKey(), idValue);
                result = namedParameterJdbcTemplate.queryForList(warpTreeSql(sqlDefine.getSelectSql(), treeOptionsParam.getpIdKey()), paramMap);
                break;
            case TreeNodeHandleType.TREEHANDLETYPE_SELF:
                paramMap.put(treeOptionsParam.getIdKey(), idValue);
                result = namedParameterJdbcTemplate.queryForList(warpTreeSql(sqlDefine.getSelectSql(), treeOptionsParam.getIdKey()), paramMap);
                break;
        }
        ConditionParam conditionDto = new ConditionParam();
        conditionDto.setField(treeOptionsParam.getForeignKey());
        conditionDto.setExpression(SqlExpression.IN);
        //TODO 换成in语句
        conditionDto.setValue(appendIds(result,treeOptionsParam.getIdKey()));
        return conditionDto;
    }

    /**
     * 拼接in字符
     * @param mapList
     * @param key
     * @return
     */
    private String appendIds(List<Map<String,Object>> mapList,String key){
        StringBuffer sb = new StringBuffer();
        for(Map<String,Object> item : mapList){
            sb.append(item.get(key)).append(",");
        }
        return StringUtils.isBlank(sb) ? "#000000" : sb.deleteCharAt(sb.lastIndexOf(",")).toString();
    }

    /**
     * tree查询SQL
     * @param sql
     * @param field
     * @return
     */
    private String warpTreeSql(String sql,String field){
        return  new StringBuffer("select t.* from (")
                .append(sql).append(") t WHERE T.").append(field).append(" =:").append(field).toString();
    }

    /**
     * 根据nodeId获取所有子节点
     * @param sql
     * @param pId
     * @param treeVo
     * @return
     */
    private List<Map<String, Object>> findAllNode(String sql,Object pId,TreeOptionsParam treeVo){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(treeVo.getpIdKey(), pId);
        List<Map<String, Object>> queryResult = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> result = namedParameterJdbcTemplate.queryForList(sql, paramMap);
        if(!org.springframework.util.CollectionUtils.isEmpty(result)){
            List<Map<String, Object>> subResult = null;
            for(Map<String, Object> subMap : result){
                subResult = findAllNode(sql, subMap.get(treeVo.getIdKey()), treeVo);
                if(!org.springframework.util.CollectionUtils.isEmpty(subResult))
                    queryResult.addAll(subResult);
            }
            queryResult.addAll(result);
        }
        return queryResult;
    }

    @Override
    public Response ztree(ZtreeParamsRequest ztreeParamsRequest) {
        SqlDefine sqlDefine = this.getRepository().findBySqlId(ztreeParamsRequest.getSqlId());
        StringBuilder stringBuilder = new StringBuilder("select t.* from (");
        stringBuilder.append(sqlDefine.getSelectSql()).append(") t ");
        Map<String,Object> paraMap = new HashedMap();
        //异步加载
        if(ztreeParamsRequest.isEnable()){
            stringBuilder.append(" where t.");
            if(StringUtils.isBlank(ztreeParamsRequest.getId())){
                stringBuilder.append(ztreeParamsRequest.getpIdKey()).append(" = ''");
            }else {
                stringBuilder.append(ztreeParamsRequest.getpIdKey()).append("= :parent");
                paraMap.put("parent",ztreeParamsRequest.getId());
            }
        }
        List<Map<String, Object>> dataList = namedParameterJdbcTemplate.queryForList(stringBuilder.toString(),paraMap);

        //异步加载判断是否parent
        if(ztreeParamsRequest.isEnable()){
            for(Map<String,Object> node : dataList){
                node.put("isParent",this.isParent(node.get(ztreeParamsRequest.getIdKey()).toString(),sqlDefine,ztreeParamsRequest));
            }
        }
        return Response.SUCCESS(dataList);
    }

    /**
     * TODO leaf 优化
     * 判断是否上级节点
     * @param parent
     * @param sqlDefine
     * @param ztreeParamsRequest
     * @return
     */
    private boolean isParent(String parent,SqlDefine sqlDefine,ZtreeParamsRequest ztreeParamsRequest){
        StringBuilder stringBuilder = new StringBuilder("select t.* from (")
        .append(sqlDefine.getSelectSql()).append(") t where t.")
        .append(ztreeParamsRequest.getpIdKey()).append("= :parent");
        Map<String,Object> paraMap = new HashedMap();
        paraMap.put("parent",parent);
        List<Map<String, Object>> dataList = namedParameterJdbcTemplate.queryForList(stringBuilder.toString(),paraMap);
        return dataList.size() > 0;
    }
}
